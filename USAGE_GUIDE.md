# 🎓 论文选题管理系统 - Agentic AI 增强版

> **一站式解决方案**：CI/CD 自动化 + AI 智能助手 + 数据分析 + Docker 容器化

---

## 🚀 5分钟快速上手

### 前置要求
- ✅ JDK 8+
- ✅ Maven 3.6+
- ✅ MySQL 8.0+
- ✅ Docker & Docker Compose（可选）

---

## 📦 方式一：Docker 一键启动（推荐 ⭐）

```bash
# 1. 克隆项目
git clone https://github.com/yourusername/springboot3wd7d5i4.git
cd springboot3wd7d5i4

# 2. 启动服务（包含 MySQL + 应用）
docker-compose up -d

# 3. 初始化数据库
docker exec -i springboot-mysql mysql -uroot -p123456 < db/springboot3wd7d5i4.sql
docker exec -i springboot-mysql mysql -uroot -p123456 < db/ai_agent_tables.sql

# 4. 验证运行
curl http://localhost:8080/springboot3wd7d5i4/actuator/health

# 5. 查看日志
docker-compose logs -f app
```

**就这么简单！** 🎉

---

## 💻 方式二：本地运行

### Windows 用户

```bash
# 1. 初始化数据库
mysql -u root -p < db/springboot3wd7d5i4.sql
mysql -u root -p < db/ai_agent_tables.sql

# 2. 配置数据库连接（application.yml）
# 修改 src/main/resources/application.yml 中的数据库密码

# 3. 双击运行
start.bat
```

### Linux/Mac 用户

```bash
# 1. 初始化数据库
mysql -u root -p < db/springboot3wd7d5i4.sql
mysql -u root -p < db/ai_agent_tables.sql

# 2. 配置数据库连接
vim src/main/resources/application.yml

# 3. 赋予执行权限并运行
chmod +x start.sh
./start.sh
```

---

## 🧪 测试新功能

### 1. 测试 AI 智能助手

```bash
# 发送消息
curl -X POST http://localhost:8080/springboot3wd7d5i4/aiagent/chat/send \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "userType": "student",
    "message": "如何选择合适的课题？"
  }'

# 预期响应：AI 会回复关于选题的建议
```

### 2. 测试智能推荐

```bash
# 生成课题推荐
curl -X POST http://localhost:8080/springboot3wd7d5i4/aiagent/recommendation/generate/topics?studentId=1

# 生成导师推荐
curl -X POST http://localhost:8080/springboot3wd7d5i4/aiagent/recommendation/generate/teachers?studentId=1
```

### 3. 测试数据分析

```bash
# 获取 Dashboard 数据
curl http://localhost:8080/springboot3wd7d5i4/analytics/dashboard

# 系统概览
curl http://localhost:8080/springboot3wd7d5i4/analytics/system/overview
```

### 4. 使用测试脚本

```bash
# Linux/Mac
chmod +x test-api.sh
./test-api.sh

# Windows
test-api.bat
```

---

## 📚 核心功能说明

### 🤖 Agentic AI 功能

#### AI 智能助手
- **多轮对话**：支持上下文感知
- **智能问答**：解答论文写作、选题问题
- **会话管理**：历史记录、清除会话

**API 列表：**
```
POST /aiagent/chat/send              # 发送消息
GET  /aiagent/chat/history           # 会话历史
DELETE /aiagent/chat/session/{id}    # 清除会话
```

#### AI 智能推荐
- **课题推荐**：基于专业兴趣匹配
- **导师推荐**：研究方向匹配
- **个性化**：每个用户的推荐不同

**API 列表：**
```
POST /aiagent/recommendation/generate/topics      # 课题推荐
POST /aiagent/recommendation/generate/teachers    # 导师推荐
GET  /aiagent/recommendation/list                 # 获取推荐
POST /aiagent/recommendation/markRead/{id}        # 标记已读
```

### 📊 数据分析功能

**API 列表：**
```
GET /analytics/system/overview        # 系统概览
GET /analytics/topics/by-category     # 课题分类
GET /analytics/students/by-college    # 学院分布
GET /analytics/topics/trend           # 选题趋势
GET /analytics/teachers/student-count # 教师统计
GET /analytics/kaiti/statistics       # 开题统计
GET /analytics/dashboard              # Dashboard 汇总
```

---

## 🔄 CI/CD 自动化部署

### GitHub Actions 配置

推送代码到 `main` 分支后，自动触发：

```
代码提交 → 编译 → 测试 → 安全扫描 → 构建镜像 → 部署
```

### 配置步骤

1. **添加 GitHub Secrets**
   ```
   Settings → Secrets → Add new secret
   
   DEPLOY_HOST=your-server.com
   DEPLOY_USER=deploy
   DEPLOY_SSH_KEY=<粘贴你的 SSH 私钥>
   ```

2. **生成 SSH Key**
   ```bash
   ssh-keygen -t ed25519 -C "github-actions"
   ssh-copy-id -i ~/.ssh/id_ed25519.pub deploy@your-server.com
   cat ~/.ssh/id_ed25519  # 复制到 GitHub Secrets
   ```

3. **服务器准备**
   ```bash
   sudo mkdir -p /opt/springboot3wd7d5i4
   cd /opt/springboot3wd7d5i4
   # 放置 docker-compose.yml
   ```

4. **推送代码**
   ```bash
   git add .
   git commit -m "feat: 添加新功能"
   git push origin main
   ```

等待 2-3 分钟，自动部署完成！🚀

---

## 📖 文档索引

| 文档 | 说明 |
|------|------|
| [QUICKSTART.md](QUICKSTART.md) | 详细启动指南 |
| [API_DOCUMENTATION.md](API_DOCUMENTATION.md) | 完整 API 文档 |
| [DEPLOYMENT.md](DEPLOYMENT.md) | 部署配置手册 |
| [PROJECT_SUMMARY.md](PROJECT_SUMMARY.md) | 项目总结 |

---

## 🛠️ 常见问题

### Q1: 端口被占用怎么办？

修改 `application.yml`：
```yaml
server:
  port: 8081  # 改成其他端口
```

### Q2: 数据库连接失败？

检查：
1. MySQL 是否启动
2. 用户名密码是否正确
3. 数据库是否创建

```bash
# 测试连接
mysql -h localhost -u root -p
```

### Q3: Docker 启动失败？

```bash
# 查看日志
docker-compose logs

# 清理重建
docker-compose down
docker-compose build --no-cache
docker-compose up -d
```

### Q4: 如何重置数据库？

```bash
# 删除旧数据
docker-compose down -v

# 重新启动
docker-compose up -d

# 重新导入
docker exec -i springboot-mysql mysql -uroot -p123456 < db/springboot3wd7d5i4.sql
docker exec -i springboot-mysql mysql -uroot -p123456 < db/ai_agent_tables.sql
```

---

## 🎯 下一步建议

### 立即可做
1. ✅ 测试所有 API 接口
2. ✅ 查看数据分析 Dashboard
3. ✅ 体验 AI 智能助手

### 短期优化
1. 集成真实 AI API（OpenAI / 文心一言）
2. 添加前端界面展示
3. 配置 HTTPS

### 长期规划
1. 实现更智能的推荐算法
2. 添加实时通知（WebSocket）
3. 微服务架构改造

---

## 📞 获取帮助

- 📧 Email: your-email@example.com
- 🐛 Issues: [GitHub Issues](https://github.com/yourusername/springboot3wd7d5i4/issues)
- 📖 Docs: 查看项目根目录的 `.md` 文件

---

## 🌟 项目亮点

✅ **完整的 CI/CD** - 自动化构建、测试、部署  
✅ **Agentic AI** - 智能助手 + 推荐引擎  
✅ **数据分析** - 多维度统计看板  
✅ **Docker 容器化** - 一键部署  
✅ **单元测试** - JaCoCo 覆盖率报告  
✅ **安全扫描** - OWASP 依赖检查  

---

## 📝 许可证

MIT License - 自由使用、修改、分发

---

**⭐ 喜欢这个项目？给个 Star 支持一下！**

[![GitHub stars](https://img.shields.io/github/stars/yourusername/springboot3wd7d5i4?style=social)](https://github.com/yourusername/springboot3wd7d5i4)
