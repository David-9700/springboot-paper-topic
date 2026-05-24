# CI/CD 部署配置指南

## GitHub Actions 配置

### 1. 添加 Secrets

在 GitHub 仓库中，进入 **Settings → Secrets and variables → Actions**，添加以下 secrets：

#### 必需 Secrets

| Secret 名称 | 说明 | 示例 |
|------------|------|------|
| `DEPLOY_HOST` | 部署服务器地址 | `192.168.1.100` 或 `example.com` |
| `DEPLOY_USER` | SSH 登录用户名 | `deploy` 或 `root` |
| `DEPLOY_SSH_KEY` | SSH 私钥内容 | 见下方生成方法 |

### 2. 生成 SSH Key

```bash
# 在本地生成 SSH key
ssh-keygen -t ed25519 -C "github-actions@deploy"

# 将公钥添加到服务器
ssh-copy-id -i ~/.ssh/id_ed25519.pub deploy@your-server.com

# 查看私钥（复制到 GitHub Secrets）
cat ~/.ssh/id_ed25519
```

### 3. 服务器端准备

```bash
# 在服务器上执行
sudo mkdir -p /opt/springboot3wd7d5i4
cd /opt/springboot3wd7d5i4

# 安装 Docker
curl -fsSL https://get.docker.com | sh
sudo systemctl enable docker
sudo systemctl start docker

# 安装 Docker Compose
sudo curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

# 创建 docker-compose.yml
# （从项目根目录复制 docker-compose.yml 到服务器）
```

### 4. 测试部署

推送代码到 main 分支后，GitHub Actions 会自动：
1. 运行测试
2. 构建 Docker 镜像
3. 推送到 GitHub Container Registry
4. SSH 连接到服务器
5. 拉取最新镜像并重启服务

---

## 手动部署（不使用 CI/CD）

### 方式一：Docker Compose

```bash
# 1. 克隆代码
git clone https://github.com/yourusername/springboot3wd7d5i4.git
cd springboot3wd7d5i4

# 2. 配置环境变量
cp .env.example .env
vim .env  # 修改数据库密码等配置

# 3. 启动服务
docker-compose up -d

# 4. 初始化数据库
docker exec -i springboot-mysql mysql -uroot -p123456 < db/springboot3wd7d5i4.sql
docker exec -i springboot-mysql mysql -uroot -p123456 < db/ai_agent_tables.sql

# 5. 验证部署
curl http://localhost:8080/springboot3wd7d5i4/actuator/health
```

### 方式二：JAR 包部署

```bash
# 1. 编译打包
mvn clean package -DskipTests

# 2. 上传 JAR 到服务器
scp target/springboot3wd7d5i4-0.0.1-SNAPSHOT.jar user@server:/opt/app/

# 3. 创建 systemd 服务
sudo vim /etc/systemd/system/springboot-app.service
```

**systemd 服务配置文件：**
```ini
[Unit]
Description=论文选题管理系统
After=syslog.target network.target mysql.service

[Service]
Type=simple
User=deploy
Group=deploy
WorkingDirectory=/opt/app
ExecStart=/usr/bin/java -jar /opt/app/springboot3wd7d5i4-0.0.1-SNAPSHOT.jar
SuccessExitStatus=143
TimeoutStopSec=10
Restart=on-failure
RestartSec=5

Environment=DB_HOST=localhost
Environment=DB_USERNAME=root
Environment=DB_PASSWORD=123456

[Install]
WantedBy=multi-user.target
```

```bash
# 4. 启动服务
sudo systemctl daemon-reload
sudo systemctl enable springboot-app
sudo systemctl start springboot-app

# 5. 查看状态
sudo systemctl status springboot-app
sudo journalctl -u springboot-app -f
```

---

## Nginx 反向代理配置（可选）

```nginx
server {
    listen 80;
    server_name your-domain.com;

    location / {
        proxy_pass http://localhost:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }

    # 静态文件
    location /static/ {
        alias /opt/app/static/;
        expires 30d;
    }
}
```

**HTTPS 配置（使用 Let's Encrypt）：**
```bash
sudo apt install certbot python3-certbot-nginx
sudo certbot --nginx -d your-domain.com
```

---

## 监控与日志

### 应用健康检查

```bash
# 健康检查端点
curl http://localhost:8080/springboot3wd7d5i4/actuator/health

# 详细信息（需要配置 management.endpoints.web.exposure.include=*）
curl http://localhost:8080/springboot3wd7d5i4/actuator/info
```

### 日志查看

```bash
# Docker 日志
docker-compose logs -f app

# Systemd 日志
journalctl -u springboot-app -f

# 应用日志文件（如果配置了）
tail -f logs/application.log
```

### 性能监控

集成 Prometheus + Grafana：

```yaml
# application.yml 添加
management:
  endpoints:
    web:
      exposure:
        include: health,info,prometheus
  metrics:
    export:
      prometheus:
        enabled: true
```

---

## 备份与恢复

### 数据库备份

```bash
# 备份脚本 backup.sh
#!/bin/bash
BACKUP_DIR="/backup/mysql"
DATE=$(date +%Y%m%d_%H%M%S)
mkdir -p $BACKUP_DIR

docker exec springboot-mysql mysqldump -uroot -p123456 springboot3wd7d5i4 > \
  $BACKUP_DIR/springboot3wd7d5i4_$DATE.sql

# 保留最近7天的备份
find $BACKUP_DIR -name "*.sql" -mtime +7 -delete
```

### 定时备份

```bash
# 添加到 crontab
crontab -e

# 每天凌晨2点备份
0 2 * * * /opt/scripts/backup.sh
```

### 数据恢复

```bash
# 恢复数据库
docker exec -i springboot-mysql mysql -uroot -p123456 springboot3wd7d5i4 < backup.sql
```

---

## 故障排查

### 常见问题

**1. 容器无法启动**
```bash
# 查看详细日志
docker-compose logs app

# 检查端口占用
netstat -tlnp | grep 8080

# 检查磁盘空间
df -h
```

**2. 数据库连接失败**
```bash
# 测试数据库连接
docker exec -it springboot-mysql mysql -uroot -p123456

# 检查 MySQL 状态
docker-compose ps mysql
```

**3. 内存不足**
```bash
# 调整 JVM 参数
JAVA_OPTS="-Xms512m -Xmx1024m"

# 在 docker-compose.yml 中添加
environment:
  JAVA_OPTS: "-Xms512m -Xmx1024m"
```

**4. 应用无响应**
```bash
# 重启服务
docker-compose restart app

# 或者
sudo systemctl restart springboot-app
```

---

## 安全加固

### 1. 防火墙配置

```bash
# 只开放必要端口
sudo ufw allow 22/tcp   # SSH
sudo ufw allow 80/tcp   # HTTP
sudo ufw allow 443/tcp  # HTTPS
sudo ufw enable
```

### 2. 数据库安全

```sql
-- 创建专用数据库用户
CREATE USER 'app_user'@'%' IDENTIFIED BY 'strong_password';
GRANT SELECT, INSERT, UPDATE, DELETE ON springboot3wd7d5i4.* TO 'app_user'@'%';
FLUSH PRIVILEGES;
```

### 3. 定期更新

```bash
# 更新依赖
mvn versions:display-dependency-updates
mvn versions:update-properties

# 更新 Docker 镜像
docker-compose pull
docker-compose up -d
```

---

## 扩展部署

### 多环境配置

```
application-dev.yml    # 开发环境
application-test.yml   # 测试环境
application-prod.yml   # 生产环境
```

**切换环境：**
```bash
# Docker
environment:
  SPRING_PROFILES_ACTIVE: prod

# JAR 运行
java -jar app.jar --spring.profiles.active=prod
```

### 负载均衡（多实例）

```yaml
# docker-compose.yml
services:
  app:
    replicas: 3
    deploy:
      resources:
        limits:
          cpus: '1'
          memory: 1G
```

配合 Nginx 实现负载均衡：
```nginx
upstream backend {
    server localhost:8081;
    server localhost:8082;
    server localhost:8083;
}

server {
    location / {
        proxy_pass http://backend;
    }
}
```

---

## 联系支持

如遇部署问题，请：
1. 查看应用日志
2. 检查 GitHub Issues
3. 提交新的 Issue 并附上错误日志
