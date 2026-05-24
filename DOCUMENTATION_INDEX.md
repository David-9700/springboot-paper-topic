# 📚 项目文档索引

欢迎查阅毕业论文选题管理系统的完整文档！

---

## 🎯 快速开始

### 新用户
👉 [快速概览 (QUICK_OVERVIEW.md)](QUICK_OVERVIEW.md) - 5分钟了解项目核心功能

### 开发者
👉 [项目说明文档 (PROJECT_DOCUMENTATION.md)](PROJECT_DOCUMENTATION.md) - 完整的技术文档

### 运维人员
👉 [AI 集成指南 (AI_INTEGRATION_GUIDE.md)](AI_INTEGRATION_GUIDE.md) - 部署和配置指南

---

## 📖 文档分类

### 📘 核心文档

| 文档 | 说明 | 适用人群 |
|------|------|----------|
| [PROJECT_DOCUMENTATION.md](PROJECT_DOCUMENTATION.md) | 完整项目说明，包含 CI/CD 和 AI 功能详解 | 所有人 |
| [QUICK_OVERVIEW.md](QUICK_OVERVIEW.md) | 快速概览，核心亮点总结 | 新用户 |
| [README.md](README.md) | 项目主页，快速开始指南 | 所有人 |

---

### 🤖 AI 功能文档

| 文档 | 说明 | 文件大小 |
|------|------|----------|
| [AI_INTEGRATION_GUIDE.md](AI_INTEGRATION_GUIDE.md) | AI 功能集成详细指南 | 436行 |
| [AI_INTEGRATION_COMPLETE.md](AI_INTEGRATION_COMPLETE.md) | AI 集成完成报告 | 417行 |
| [AI_API_GUIDE.md](AI_API_GUIDE.md) | AI API 配置详解 | 278行 |
| [AI_QUICK_START.md](AI_QUICK_START.md) | AI 功能快速开始 | 313行 |

**主要内容**:
- ✅ DeepSeek / 文心一言双引擎配置
- ✅ AI 对话、推荐、分析功能实现
- ✅ 前端组件集成方法
- ✅ API 接口文档
- ✅ 常见问题解答

---

### 🔧 DevOps 文档

| 文档 | 说明 |
|------|------|
| [.github/workflows/maven.yml](.github/workflows/maven.yml) | CI/CD 流水线配置 |
| [Dockerfile](Dockerfile) | Docker 镜像定义 |
| [docker-compose.yml](docker-compose.yml) | 容器编排配置 |

**CI/CD 流程**:
```
Build → Test → Security Scan → Deploy
  ↓       ↓         ↓            ↓
Maven  JUnit 5   OWASP      Docker + K8s
```

---

### 🛠️ 开发文档

| 文档 | 说明 |
|------|------|
| [pom.xml](pom.xml) | Maven 依赖配置 |
| [src/main/resources/application.yml](src/main/resources/application.yml) | 应用配置文件 |
| [db/springboot3wd7d5i4.sql](db/springboot3wd7d5i4.sql) | 数据库脚本 |
| [db/ai_agent_tables.sql](db/ai_agent_tables.sql) | AI 功能表结构 |

---

### 📸 截图文档

| 文档 | 说明 |
|------|------|
| [docs/screenshots/README_SCREENSHOTS.md](docs/screenshots/README_SCREENSHOTS.md) | 截图准备指南 |

**需要的截图** (共15张):
- CI/CD: 5张（构建、测试、覆盖率、Docker、部署）
- AI 功能: 10张（聊天、推荐、数据面板、教师工具等）

---

### ❓ 帮助文档

| 文档 | 说明 |
|------|------|
| [TROUBLESHOOTING.md](TROUBLESHOOTING.md) | 常见问题修复指南 |
| [WINDOWS_SETUP.md](WINDOWS_SETUP.md) | Windows 环境配置 |
| [DATABASE_SETUP.md](DATABASE_SETUP.md) | 数据库设置指南 |
| [README_WINDOWS.md](README_WINDOWS.md) | Windows 快速启动 |

---

## 🗺️ 文档阅读路径

### 路径 1: 想了解项目整体情况
```
QUICK_OVERVIEW.md 
    ↓
PROJECT_DOCUMENTATION.md
    ↓
AI_INTEGRATION_COMPLETE.md
```

### 路径 2: 想部署系统
```
WINDOWS_SETUP.md (Windows用户)
    ↓
DATABASE_SETUP.md
    ↓
AI_INTEGRATION_GUIDE.md
    ↓
PROJECT_DOCUMENTATION.md → 部署指南章节
```

### 路径 3: 想开发新功能
```
PROJECT_DOCUMENTATION.md → 系统架构章节
    ↓
AI_INTEGRATION_GUIDE.md → 扩展开发章节
    ↓
查看现有代码实现
```

### 路径 4: 遇到问题
```
TROUBLESHOOTING.md
    ↓
根据问题类型选择对应文档
    ↓
GitHub Issues
```

---

## 📊 文档统计

| 类型 | 数量 | 总行数 |
|------|------|--------|
| 核心文档 | 3 | ~2000 |
| AI 文档 | 4 | ~1444 |
| 帮助文档 | 4 | ~950 |
| 配置文件 | 5 | ~500 |
| **总计** | **16** | **~4894** |

---

## 🎓 推荐阅读顺序

### 第一次接触项目

1. **QUICK_OVERVIEW.md** (5分钟)
   - 了解项目核心价值
   - 快速浏览功能特性

2. **PROJECT_DOCUMENTATION.md** (30分钟)
   - 深入理解系统架构
   - 学习 CI/CD 和 AI 实现

3. **AI_INTEGRATION_GUIDE.md** (15分钟)
   - 掌握 AI 功能使用方法
   - 了解配置和部署

### 准备部署

1. **WINDOWS_SETUP.md** 或相应环境文档
2. **DATABASE_SETUP.md**
3. **AI_API_GUIDE.md**
4. 按照 **PROJECT_DOCUMENTATION.md** 的部署指南操作

### 开始开发

1. **PROJECT_DOCUMENTATION.md** → 系统架构
2. 阅读关键源代码
3. **AI_INTEGRATION_GUIDE.md** → 扩展开发
4. 参考现有实现

---

## 🔗 外部资源

### 技术文档
- [Spring Boot 官方文档](https://spring.io/projects/spring-boot)
- [Vue.js 官方文档](https://vuejs.org/)
- [MyBatis Plus 文档](https://baomidou.com/)
- [ECharts 文档](https://echarts.apache.org/)
- [GitHub Actions 文档](https://docs.github.com/en/actions)
- [Docker 文档](https://docs.docker.com/)

### AI API
- [DeepSeek API 文档](https://platform.deepseek.com/)
- [文心一言 API 文档](https://cloud.baidu.com/doc/WENXINWORKSHOP/)

---

## 📝 文档更新记录

| 日期 | 版本 | 更新内容 |
|------|------|----------|
| 2026-05-15 | v1.0 | 初始版本，完整项目文档 |
| 2026-05-15 | v1.0 | CI/CD 和 AI 功能文档 |
| 2026-05-15 | v1.0 | 截图指南和帮助文档 |

---

## 💡 使用建议

1. **善用搜索**: 使用 Ctrl+F 快速查找关键词
2. **按需阅读**: 不必从头到尾读完所有文档
3. **实践为主**: 边看文档边操作效果更好
4. **反馈改进**: 发现文档问题请提 Issue

---

## 📞 获取帮助

- 📧 Email: support@example.com
- 💬 GitHub Issues: [提交问题](https://github.com/yourusername/springboot-paper-topic/issues)
- 📖 Wiki: [项目 Wiki](https://github.com/yourusername/springboot-paper-topic/wiki)

---

**祝您使用愉快！** 🎉

*最后更新: 2026-05-15*
