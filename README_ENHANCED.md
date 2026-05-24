# 论文选题管理系统 - Agentic AI 增强版

[![Java CI/CD Pipeline](https://github.com/yourusername/springboot3wd7d5i4/actions/workflows/maven.yml/badge.svg)](https://github.com/yourusername/springboot3wd7d5i4/actions)
[![Docker Build](https://img.shields.io/docker/builds/yourusername/springboot3wd7d5i4)](https://hub.docker.com/r/yourusername/springboot3wd7d5i4)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

## 📖 项目简介

这是一个基于 Spring Boot 的**论文选题管理系统**，现已集成 **Agentic AI 智能助手**和**数据分析功能**，提供智能化的课题推荐、导师匹配和数据可视化能力。

### ✨ 核心特性

- ✅ **完整的论文选题流程管理** - 从课题发布到最终审核的全流程
- 🤖 **AI 智能助手** - 基于对话的智能问答系统
- 🎯 **智能推荐引擎** - 个性化课题和导师推荐
- 📊 **数据分析看板** - 实时统计和趋势分析
- 🔄 **CI/CD 自动化** - GitHub Actions 自动构建、测试、部署
- 🐳 **Docker 容器化** - 一键部署，开箱即用
- 🧪 **单元测试覆盖** - JaCoCo 测试覆盖率报告
- 🔒 **安全扫描** - OWASP 依赖漏洞检测

---

## 🚀 快速开始

详细启动指南请查看: [QUICKSTART.md](QUICKSTART.md)

### 方式一：Docker（推荐）

```bash
# 一键启动
docker-compose up -d

# 访问应用
curl http://localhost:8080/springboot3wd7d5i4/actuator/health
```

### 方式二：本地运行

```bash
# Windows
start.bat

# Linux/Mac
./start.sh
```

---

## 📋 主要功能模块

### 1. 基础业务模块

| 模块 | 说明 |
|------|------|
| 学生管理 | 学生信息维护、学院专业管理 |
| 教师管理 | 教师信息、指导关系管理 |
| 课题管理 | 课题发布、分类、审核 |
| 选题流程 | 学生选题、教师审核、结果确认 |
| 开题报告 | 报告提交、审核、成绩管理 |
| 任务书管理 | 任务书下发、接收 |
| 消息通知 | 系统消息、互动交流 |
| 新闻公告 | 信息发布、分类管理 |

### 2. Agentic AI 模块 ⭐ 新增

#### AI 智能助手
- **多轮对话**: 支持上下文感知的对话会话
- **智能问答**: 解答论文写作、选题相关问题
- **会话管理**: 历史记录查询、会话清除

**API 示例:**
```bash
# 发送消息
curl -X POST http://localhost:8080/springboot3wd7d5i4/aiagent/chat/send \
  -H "Content-Type: application/json" \
  -d '{"userId":1,"userType":"student","message":"如何选择合适的课题？"}'
```

#### AI 智能推荐
- **课题推荐**: 基于学生专业和兴趣的个性化推荐
- **导师推荐**: 研究方向匹配的导师推荐
- **推荐管理**: 已读标记、推荐列表查询

**API 示例:**
```bash
# 生成课题推荐
curl -X POST http://localhost:8080/springboot3wd7d5i4/aiagent/recommendation/generate/topics?studentId=1
```

### 3. 数据分析模块 ⭐ 新增

- **系统概览**: 学生、教师、课题数量统计
- **课题分类**: 各类别课题分布
- **学院分布**: 各学院学生人数
- **选题趋势**: 按月统计选题情况
- **教师指导**: 教师指导学生数量排行
- **开题统计**: 提交率、通过率分析
- **Dashboard**: 一站式数据汇总

**API 示例:**
```bash
# 获取 Dashboard 数据
curl http://localhost:8080/springboot3wd7d5i4/analytics/dashboard
```

---

## 🏗️ 技术架构

### 后端技术栈

- **核心框架**: Spring Boot 2.2.2
- **持久层**: MyBatis Plus 2.3 + MySQL 8.0
- **安全框架**: Apache Shiro 1.3.2
- **工具库**: 
  - FastJSON 1.2.8 (JSON 处理)
  - Hutool 4.0.12 (工具类)
  - Apache POI (Excel 处理)
  - Apache HttpClient (HTTP 请求)
- **监控**: Spring Boot Actuator
- **测试**: JUnit 5 + Mockito + JaCoCo

### DevOps 工具链

- **CI/CD**: GitHub Actions
- **容器化**: Docker + Docker Compose
- **安全扫描**: OWASP Dependency Check
- **代码质量**: JaCoCo 测试覆盖率

---

## 📂 项目结构

```
springboot3wd7d5i4/
├── .github/workflows/          # CI/CD 配置
│   └── maven.yml               # GitHub Actions 流水线
├── db/                         # 数据库脚本
│   ├── springboot3wd7d5i4.sql  # 基础数据
│   └── ai_agent_tables.sql     # AI Agent 表结构
├── src/
│   ├── main/
│   │   ├── java/com/
│   │   │   ├── controller/     # 控制器层
│   │   │   │   ├── AiAgentChatController.java        # AI 聊天
│   │   │   │   ├── AiAgentRecommendationController.java  # AI 推荐
│   │   │   │   └── AnalyticsController.java          # 数据分析
│   │   │   ├── service/        # 服务层
│   │   │   ├── dao/            # 数据访问层
│   │   │   └── entity/         # 实体类
│   │   └── resources/
│   │       ├── mapper/         # MyBatis XML
│   │       └── application.yml # 配置文件
│   └── test/                   # 单元测试
├── Dockerfile                  # Docker 镜像构建
├── docker-compose.yml          # Docker Compose 配置
├── pom.xml                     # Maven 配置
├── start.bat / start.sh        # 启动脚本
├── API_DOCUMENTATION.md        # API 文档
└── QUICKSTART.md               # 快速开始指南
```

---

## 🔧 配置说明

### 环境变量

| 变量名 | 说明 | 默认值 |
|--------|------|--------|
| `DB_HOST` | 数据库主机 | `localhost` |
| `DB_USERNAME` | 数据库用户名 | `root` |
| `DB_PASSWORD` | 数据库密码 | `123456` |
| `SPRING_PROFILES` | Spring Profile | `prod` |

### application.yml 关键配置

```yaml
server:
  port: 8080
  servlet:
    context-path: /springboot3wd7d5i4

spring:
  datasource:
    url: jdbc:mysql://${DB_HOST:localhost}:3306/springboot3wd7d5i4
    username: ${DB_USERNAME:root}
    password: ${DB_PASSWORD:123456}
```

---

## 🧪 测试与质量

### 运行测试

```bash
# 运行所有测试
mvn test

# 生成覆盖率报告
mvn jacoco:report

# 安全检查
mvn dependency-check:check
```

### 测试覆盖率

JaCoCo 报告会生成在 `target/site/jacoco/index.html`

---

## 🚢 部署指南

### GitHub Actions CI/CD

流水线包含三个阶段：

1. **Build & Test**: 编译、测试、生成覆盖率报告
2. **Security Scan**: OWASP 依赖安全检查
3. **Deploy**: 构建 Docker 镜像并部署

### 配置部署 Secrets

在 GitHub 仓库 Settings → Secrets 中添加：

```
DEPLOY_HOST=your-server.com
DEPLOY_USER=deploy
DEPLOY_SSH_KEY=<your-ssh-private-key>
```

### 服务器端配置

```bash
# 创建部署目录
mkdir -p /opt/springboot3wd7d5i4
cd /opt/springboot3wd7d5i4

# 放置 docker-compose.yml
# 启动服务
docker-compose up -d
```

---

## 📊 API 文档

完整 API 文档请查看: [API_DOCUMENTATION.md](API_DOCUMENTATION.md)

### 核心接口速览

| 接口 | 方法 | 说明 |
|------|------|------|
| `/aiagent/chat/send` | POST | 发送 AI 聊天消息 |
| `/aiagent/chat/history` | GET | 获取会话历史 |
| `/aiagent/recommendation/generate/topics` | POST | 生成课题推荐 |
| `/aiagent/recommendation/generate/teachers` | POST | 生成导师推荐 |
| `/analytics/dashboard` | GET | 获取 Dashboard 数据 |
| `/analytics/system/overview` | GET | 系统概览统计 |

---

## 🛠️ 开发指南

### 添加新的 AI 功能

1. 创建 Entity 类
2. 创建 Dao 接口
3. 创建 Service 接口和实现
4. 创建 Controller
5. 添加 MyBatis XML 映射
6. 编写单元测试
7. 更新数据库脚本

### 扩展推荐算法

修改 `AiAgentRecommendationServiceImpl.java` 中的推荐逻辑，可以集成：
- 协同过滤算法
- 基于内容的推荐
- 机器学习模型
- 外部 AI API

---

## 📈 性能优化建议

1. **数据库索引**: 已为常用查询字段添加索引
2. **缓存机制**: 可集成 Redis 缓存热点数据
3. **分页查询**: 所有列表接口支持分页
4. **连接池**: 使用 HikariCP 数据库连接池
5. **异步处理**: 推荐使用 @Async 处理耗时操作

---

## 🔐 安全建议

1. **生产环境**: 修改默认数据库密码
2. **API 认证**: 启用 Shiro 权限控制
3. **HTTPS**: 配置 SSL 证书
4. **CORS**: 配置跨域访问策略
5. **依赖更新**: 定期运行 `mvn dependency-check:check`

---

## 🤝 贡献指南

欢迎提交 Issue 和 Pull Request！

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启 Pull Request

---

## 📝 更新日志

### v2.0.0 (2024-01-15)
- ✨ 新增 Agentic AI 智能助手
- ✨ 新增智能推荐引擎
- ✨ 新增数据分析模块
- 🚀 升级 CI/CD pipeline
- 🐳 添加 Docker 容器化支持
- 🧪 添加单元测试框架

### v1.0.0 (2023-12-01)
- 基础论文选题管理功能

---

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情

---

## 📞 联系方式

- 项目主页: [GitHub Repository](https://github.com/yourusername/springboot3wd7d5i4)
- 问题反馈: [Issues](https://github.com/yourusername/springboot3wd7d5i4/issues)
- 邮箱: your-email@example.com

---

## 🙏 致谢

感谢以下开源项目：
- [Spring Boot](https://spring.io/projects/spring-boot)
- [MyBatis Plus](https://baomidou.com/)
- [GitHub Actions](https://github.com/features/actions)
- [Docker](https://www.docker.com/)

---

**⭐ 如果这个项目对你有帮助，请给个 Star！**
