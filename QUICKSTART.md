# 快速开始指南

## 📋 前置要求

- JDK 8 或更高版本
- Maven 3.6+
- MySQL 8.0+
- Docker & Docker Compose（可选，用于容器化部署）

---

## 🚀 方式一：本地运行（传统方式）

### 1. 数据库准备

```bash
# 登录 MySQL
mysql -u root -p

# 创建数据库
CREATE DATABASE IF NOT EXISTS springboot3wd7d5i4 
DEFAULT CHARACTER SET utf8mb4 
DEFAULT COLLATE utf8mb4_unicode_ci;

# 使用数据库
USE springboot3wd7d5i4;

# 导入基础数据
source db/springboot3wd7d5i4.sql;

# 导入 AI Agent 相关表
source db/ai_agent_tables.sql;
```

### 2. 配置数据库连接

编辑 `src/main/resources/application.yml`：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/springboot3wd7d5i4?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
    username: root
    password: 你的密码
```

### 3. 编译并运行

**Windows:**
```bash
start.bat
```

**Linux/Mac:**
```bash
chmod +x start.sh
./start.sh
```

**或者手动执行:**
```bash
mvn clean package
mvn spring-boot:run
```

### 4. 访问应用

- 后端 API: http://localhost:8080/springboot3wd7d5i4
- 健康检查: http://localhost:8080/springboot3wd7d5i4/actuator/health

---

## 🐳 方式二：Docker 运行（推荐）

### 1. 构建并启动容器

```bash
# 一键启动（包含 MySQL 和应用）
docker-compose up -d

# 查看日志
docker-compose logs -f app

# 查看运行状态
docker-compose ps
```

### 2. 初始化数据库

```bash
# 进入 MySQL 容器
docker exec -it springboot-mysql mysql -uroot -p123456

# 在 MySQL 中执行
USE springboot3wd7d5i4;
source /docker-entrypoint-initdb.d/springboot3wd7d5i4.sql;
source /docker-entrypoint-initdb.d/ai_agent_tables.sql;
```

### 3. 停止服务

```bash
docker-compose down

# 如需删除数据卷（谨慎使用）
docker-compose down -v
```

---

## 🧪 运行测试

```bash
# 运行所有测试
mvn test

# 生成测试覆盖率报告
mvn jacoco:report

# 查看报告
# 打开 target/site/jacoco/index.html
```

---

## 📊 新增功能说明

### 1. Agentic AI 功能

#### AI 智能助手
- **发送消息**: `POST /aiagent/chat/send`
- **会话历史**: `GET /aiagent/chat/history?sessionId=xxx`
- **清除会话**: `DELETE /aiagent/chat/session/{sessionId}`

#### AI 智能推荐
- **课题推荐**: `POST /aiagent/recommendation/generate/topics?studentId=1`
- **导师推荐**: `POST /aiagent/recommendation/generate/teachers?studentId=1`
- **获取推荐**: `GET /aiagent/recommendation/list?userId=1&userType=student`

### 2. 数据分析功能

- **系统概览**: `GET /analytics/system/overview`
- **课题分类**: `GET /analytics/topics/by-category`
- **学院分布**: `GET /analytics/students/by-college`
- **选题趋势**: `GET /analytics/topics/trend`
- **Dashboard**: `GET /analytics/dashboard`

详细 API 文档请查看: [API_DOCUMENTATION.md](API_DOCUMENTATION.md)

---

## 🔧 CI/CD Pipeline

项目已配置 GitHub Actions 自动化流程：

### 触发条件
- Push 到 `main` 或 `develop` 分支
- Pull Request 到 `main` 分支
- Release 发布

### 流水线阶段

1. **Build & Test**
   - 代码编译
   - 单元测试
   - 测试覆盖率报告

2. **Security Scan**
   - 依赖安全检查（OWASP）
   - CVSS >= 7 的漏洞会阻止构建

3. **Deploy**（仅 main 分支）
   - 构建 Docker 镜像
   - 推送到 GitHub Container Registry
   - SSH 自动部署到服务器

### 配置 Secrets

在 GitHub 仓库设置中添加以下 Secrets：
- `DEPLOY_HOST`: 部署服务器地址
- `DEPLOY_USER`: SSH 用户名
- `DEPLOY_SSH_KEY`: SSH 私钥

---

## 🛠️ 技术栈

- **后端框架**: Spring Boot 2.2.2
- **持久层**: MyBatis Plus 2.3
- **数据库**: MySQL 8.0
- **测试框架**: JUnit 5 + Mockito
- **CI/CD**: GitHub Actions
- **容器化**: Docker + Docker Compose
- **监控**: Spring Boot Actuator

---

## 📝 常见问题

### 1. 端口冲突
如果 8080 端口被占用，修改 `application.yml` 中的 `server.port`

### 2. 数据库连接失败
- 检查 MySQL 是否启动
- 确认数据库名称、用户名、密码正确
- 检查防火墙设置

### 3. Maven 依赖下载慢
配置阿里云镜像（`~/.m2/settings.xml`）：
```xml
<mirrors>
  <mirror>
    <id>aliyun</id>
    <mirrorOf>central</mirrorOf>
    <url>https://maven.aliyun.com/repository/public</url>
  </mirror>
</mirrors>
```

### 4. Docker 构建失败
- 确保 Docker Desktop 正在运行
- 清理缓存: `docker system prune -a`
- 重新构建: `docker-compose build --no-cache`

---

## 📞 支持

如有问题，请查看：
- 应用日志: `logs/` 目录或 `docker-compose logs`
- 测试报告: `target/surefire-reports/`
- 覆盖率报告: `target/site/jacoco/index.html`

---

## 🎯 下一步计划

1. 集成真实 AI API（OpenAI / 文心一言）
2. 添加 WebSocket 实时通信
3. 实现更智能的推荐算法
4. 添加数据可视化大屏
5. 实现工作流引擎
