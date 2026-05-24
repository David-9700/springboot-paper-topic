# 🚀 快速参考卡片

## 📌 一键启动

### Docker（推荐）
```bash
docker-compose up -d
```

### 本地运行
```bash
# Windows
start.bat

# Linux/Mac
./start.sh
```

---

## 🔑 核心 API（23个新接口）

### AI 聊天（8个）
```bash
# 发送消息
POST /aiagent/chat/send
Body: {"userId":1,"userType":"student","message":"如何选题？"}

# 会话历史
GET /aiagent/chat/history?sessionId=xxx&page=1&limit=20

# 清除会话
DELETE /aiagent/chat/session/{sessionId}
```

### AI 推荐（7个）
```bash
# 生成课题推荐
POST /aiagent/recommendation/generate/topics?studentId=1

# 生成导师推荐
POST /aiagent/recommendation/generate/teachers?studentId=1

# 获取推荐列表
GET /aiagent/recommendation/list?userId=1&userType=student&page=1&limit=10
```

### 数据分析（8个）
```bash
# Dashboard 汇总
GET /analytics/dashboard

# 系统概览
GET /analytics/system/overview

# 课题分类
GET /analytics/topics/by-category

# 选题趋势
GET /analytics/topics/trend
```

---

## 🧪 快速测试

### 健康检查
```bash
curl http://localhost:8080/springboot3wd7d5i4/actuator/health
```

### 测试 AI 聊天
```bash
curl -X POST http://localhost:8080/springboot3wd7d5i4/aiagent/chat/send \
  -H "Content-Type: application/json" \
  -d '{"userId":1,"userType":"student","message":"你好"}'
```

### 运行测试脚本
```bash
# Linux/Mac
./test-api.sh

# Windows
test-api.bat
```

---

## 📚 文档速查

| 需求 | 查看文档 |
|------|----------|
| 快速上手 | QUICKSTART.md |
| API 详情 | API_DOCUMENTATION.md |
| 部署配置 | DEPLOYMENT.md |
| 项目总结 | FINAL_REPORT.md |
| 交付清单 | DELIVERY_CHECKLIST.md |

---

## 🐳 Docker 常用命令

```bash
# 启动
docker-compose up -d

# 停止
docker-compose down

# 查看日志
docker-compose logs -f app

# 重启
docker-compose restart

# 查看状态
docker-compose ps

# 进入容器
docker exec -it springboot-app bash

# 重建镜像
docker-compose build --no-cache
```

---

## 🔧 数据库初始化

```bash
# 本地 MySQL
mysql -u root -p < db/springboot3wd7d5i4.sql
mysql -u root -p < db/ai_agent_tables.sql

# Docker MySQL
docker exec -i springboot-mysql mysql -uroot -p123456 < db/springboot3wd7d5i4.sql
docker exec -i springboot-mysql mysql -uroot -p123456 < db/ai_agent_tables.sql
```

---

## 📊 监控端点

```bash
# 健康检查
GET /actuator/health

# 应用信息
GET /actuator/info

# 指标数据
GET /actuator/metrics
```

---

## ⚙️ 配置文件

### 数据库配置
`src/main/resources/application.yml`
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/springboot3wd7d5i4
    username: root
    password: 123456
```

### 环境变量
`.env`
```env
DB_PASSWORD=123456
SPRING_PROFILES=prod
```

---

## 🎯 常见问题

### Q: 端口被占用？
```yaml
# 修改 application.yml
server:
  port: 8081
```

### Q: 数据库连接失败？
```bash
# 检查 MySQL 是否运行
mysql -u root -p

# 检查配置
cat src/main/resources/application.yml
```

### Q: Docker 启动失败？
```bash
# 查看日志
docker-compose logs

# 清理重建
docker-compose down -v
docker-compose up -d
```

---

## 📞 技术支持

- 📖 文档: 查看项目根目录 .md 文件
- 🐛 Issue: GitHub Issues
- 📧 Email: your-email@example.com

---

**版本**: v2.0.0 | **更新**: 2024-01-15
