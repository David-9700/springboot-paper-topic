# 🚀 GitHub CI/CD 完整配置教程

## 📖 目录
1. [CI/CD 概念图解](#cicd-概念图解)
2. [项目中的 CI/CD 流程](#项目中的-cicd-流程)
3. [详细配置步骤](#详细配置步骤)
4. [常见问题与解决](#常见问题与解决)
5. [最佳实践](#最佳实践)

---

## CI/CD 概念图解

### 什么是 CI/CD？

```
传统开发流程：
代码编写 → 手动编译 → 手动测试 → 手动部署 → 上线
   ↓         ↓           ↓           ↓
 耗时较长   容易出错    容易遗漏    风险较高

CI/CD 自动化流程：
代码提交 → 自动编译 → 自动测试 → 自动部署 → 上线
   ↓         ↓           ↓           ↓
 即时反馈   准确可靠    全面覆盖    快速迭代
```

### CI/CD 的优势

| 特性 | 说明 | 效果 |
|------|------|------|
| ⚡ **自动化** | 代码推送自动触发流水线 | 节省 80% 时间 |
| 🔒 **质量保证** | 每次提交都运行测试 | Bug 减少 60% |
| 🛡️ **安全扫描** | 自动检测依赖漏洞 | 提前发现风险 |
| 🚀 **快速交付** | 从代码到部署仅需 5-8 分钟 | 迭代速度提升 3 倍 |
| 📊 **可视化** | 实时查看构建状态 | 问题定位更快 |
| 🔄 **可回滚** | Docker 镜像版本化管理 | 故障恢复 < 1 分钟 |

---

## 项目中的 CI/CD 流程

### 整体架构图

```
┌──────────────────────────────────────────────────────────────┐
│                     开发者本地                                │
│                                                               │
│  编写代码 → git commit → git push                            │
└────────────────────┬─────────────────────────────────────────┘
                     │
                     ↓
┌──────────────────────────────────────────────────────────────┐
│                   GitHub Actions                              │
│                                                               │
│  ┌─────────────┐     ┌──────────────┐     ┌──────────────┐  │
│  │  Job 1      │────→│  Job 2       │────→│  Job 3       │  │
│  │  Build      │     │  Security    │     │  Deploy      │  │
│  │  & Test     │     │  Scan        │     │              │  │
│  └─────────────┘     └──────────────┘     └──────────────┘  │
│       ↓                    ↓                    ↓            │
│  ✅ Maven 编译        🔍 OWASP 检查       🚀 Docker 推送    │
│  ✅ 单元测试          🛡️ 漏洞扫描         🌐 SSH 部署       │
│  ✅ 覆盖率报告                             📦 容器化运行     │
└────────────────────┬─────────────────────────────────────────┘
                     │
                     ↓
┌──────────────────────────────────────────────────────────────┐
│                   生产服务器                                   │
│                                                               │
│  Docker Hub/GHCR ← 拉取镜像 ← docker-compose up -d           │
└──────────────────────────────────────────────────────────────┘
```

### 三阶段详细说明

#### 🏗️ 阶段 1：Build & Test（构建与测试）

**触发条件**：代码推送到 `main` 或 `develop` 分支

**执行步骤**：
```yaml
1. Checkout Code          # 下载最新代码
2. Setup JDK 8            # 配置 Java 环境
3. Cache Maven packages   # 使用缓存加速
4. Build Project          # mvn clean compile
5. Run Unit Tests         # mvn test
6. Generate Coverage      # mvn jacoco:report
7. Upload Artifacts       # 保存测试结果
```

**预期输出**：
```
✅ BUILD SUCCESS
📊 Test Results: 45 passed, 0 failed
📈 Code Coverage: 82%
```

---

#### 🔍 阶段 2：Security Scan（安全扫描）

**触发条件**：阶段 1 成功完成

**执行步骤**：
```yaml
1. Checkout Code          # 下载代码
2. Setup JDK 8            # 配置环境
3. Dependency Check       # mvn dependency-check:check
```

**扫描内容**：
- ✅ 已知安全漏洞（CVE）
- ✅ 许可证合规性
- ✅ 过时依赖警告

**预期输出**：
```
🛡️ Dependency Check: PASSED
⚠️  Found 0 critical vulnerabilities
✅  All dependencies are safe
```

---

#### 🚀 阶段 3：Deploy（部署）

**触发条件**：
- 阶段 1 和 2 都成功
- 推送到 `main` 分支

**执行步骤**：
```yaml
1. Login to GHCR          # 登录容器仓库
2. Build Docker Image     # 构建镜像
3. Push to Registry       # 推送镜像
4. SSH to Server          # 连接服务器
5. docker-compose pull    # 拉取最新镜像
6. docker-compose up -d   # 重启服务
7. Health Check           # 健康检查
```

**预期输出**：
```
🐳 Docker image built successfully
📤 Pushed to ghcr.io/username/springboot-paper-topic:latest
🔗 Connected to production server
✅ Service is running on port 8080
```

---

## 详细配置步骤

### 步骤 1：创建 GitHub 仓库

```bash
# 1. 在 GitHub 上创建新仓库
# 访问 https://github.com/new

# 2. 初始化本地 Git
cd D:\project\springboot3wd7d5i4
git init

# 3. 添加远程仓库
git remote add origin https://github.com/你的用户名/springboot-paper-topic.git

# 4. 提交代码
git add .
git commit -m "Initial commit with CI/CD"
git branch -M main
git push -u origin main
```

### 步骤 2：配置文件已存在

项目中已经包含完整的 CI/CD 配置：

**文件位置**：`.github/workflows/maven.yml`

**主要配置**：
```yaml
name: Java CI/CD Pipeline

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main ]

jobs:
  build-and-test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - uses: actions/setup-java@v3
        with:
          java-version: '8'
      - run: mvn clean compile -B
      - run: mvn test -B
      - run: mvn jacoco:report

  security-scan:
    needs: build-and-test
    runs-on: ubuntu-latest
    steps:
      - run: mvn dependency-check:check

  deploy:
    needs: [build-and-test, security-scan]
    if: github.ref == 'refs/heads/main'
    runs-on: ubuntu-latest
    steps:
      - uses: docker/build-push-action@v4
      - uses: appleboy/ssh-action@v0.1.10
```

### 步骤 3：创建必需的配置文件

#### 📄 Dockerfile（项目根目录）

```dockerfile
FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:8080/springboot3wd7d5i4/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
```

#### 📄 docker-compose.yml（项目根目录）

```yaml
version: '3.8'

services:
  app:
    image: ghcr.io/${GITHUB_USERNAME}/springboot-paper-topic:latest
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=prod
      - DB_HOST=mysql
      - DB_USERNAME=root
      - DB_PASSWORD=${DB_PASSWORD}
    depends_on:
      - mysql
    restart: unless-stopped
    networks:
      - app-network
  
  mysql:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: ${DB_PASSWORD}
      MYSQL_DATABASE: springboot3wd7d5i4
    volumes:
      - mysql-data:/var/lib/mysql
      - ./db:/docker-entrypoint-initdb.d
    ports:
      - "3306:3306"
    restart: unless-stopped
    networks:
      - app-network

volumes:
  mysql-data:

networks:
  app-network:
    driver: bridge
```

#### 📄 .env（项目根目录，不提交到 Git）

```bash
GITHUB_USERNAME=你的GitHub用户名
DB_PASSWORD=你的数据库密码
```

添加到 `.gitignore`：
```
.env
```

### 步骤 4：配置 GitHub Secrets

#### 方式 A：使用 GitHub Container Registry (GHCR) - 推荐

只需配置部署相关的 Secrets：

1. 进入仓库 → **Settings** → **Secrets and variables** → **Actions**
2. 点击 **New repository secret**
3. 添加以下 Secrets：

| Secret | 值 | 示例 |
|--------|-----|------|
| `DEPLOY_HOST` | 服务器 IP | `192.168.1.100` |
| `DEPLOY_USER` | SSH 用户名 | `root` |
| `DEPLOY_SSH_KEY` | SSH 私钥 | `-----BEGIN OPENSSH PRIVATE KEY-----...` |
| `DB_PASSWORD` | 数据库密码 | `your_secure_password` |

#### 生成 SSH 密钥对

```bash
# 在本地生成密钥
ssh-keygen -t ed25519 -C "github-actions@deploy"

# 将公钥添加到服务器
ssh-copy-id -i ~/.ssh/id_ed25519.pub user@your-server.com

# 查看私钥（复制到 GitHub Secrets）
cat ~/.ssh/id_ed25519
```

#### 方式 B：使用 Docker Hub

如果需要配置 Docker Hub：

| Secret | 值 |
|--------|-----|
| `DOCKER_USERNAME` | Docker Hub 用户名 |
| `DOCKER_PASSWORD` | Docker Hub Access Token |

获取 Access Token：
1. 登录 Docker Hub
2. Account Settings → Security
3. New Access Token → 生成令牌

### 步骤 5：触发第一次 CI/CD

```bash
# 提交所有配置文件
git add .
git commit -m "Add CI/CD configuration"
git push origin main
```

### 步骤 6：监控执行状态

1. **访问 Actions 页面**：
   ```
   https://github.com/你的用户名/springboot-paper-topic/actions
   ```

2. **查看实时日志**：
   - 点击正在运行的工作流
   - 展开各个 Job
   - 查看详细步骤

3. **预期结果**：
   ```
   ✅ build-and-test: Success (2m 30s)
   ✅ security-scan: Success (1m 15s)
   ✅ deploy: Success (3m 45s)
   ```

---

## 常见问题与解决

### ❌ 问题 1：CI/CD 没有自动触发

**可能原因**：
- 推送到错误的分支
- 配置文件路径错误

**解决方案**：
```bash
# 确认分支名称
git branch

# 确认配置文件位置
ls -la .github/workflows/maven.yml

# 手动触发（在 GitHub Actions 页面）
# Click "Run workflow" → Select branch → Run
```

### ❌ 问题 2：Maven 构建失败

**错误信息**：
```
[ERROR] COMPILATION ERROR
```

**解决方案**：
1. 查看完整日志
2. 本地测试构建：
```bash
mvn clean compile
```
3. 修复编译错误后重新推送

### ❌ 问题 3：Docker 推送失败

**错误信息**：
```
unauthorized: authentication required
```

**解决方案**：
1. 检查 Secrets 是否正确配置
2. 确认 GITHUB_TOKEN 权限
3. 在 workflow 中使用正确的认证方式

### ❌ 问题 4：SSH 部署失败

**错误信息**：
```
Permission denied (publickey)
```

**解决方案**：
```bash
# 测试 SSH 连接
ssh -i ~/.ssh/id_ed25519 user@server.com

# 确保服务器 authorized_keys 包含公钥
cat ~/.ssh/id_ed25519.pub >> ~/.ssh/authorized_keys
chmod 600 ~/.ssh/authorized_keys
```

### ❌ 问题 5：数据库迁移失败

**错误信息**：
```
Table 'springboot3wd7d5i4.users' doesn't exist
```

**解决方案**：
1. 确认 `db/` 目录下有 SQL 文件
2. 检查 `docker-compose.yml` 卷挂载配置
3. 手动导入数据：
```bash
docker exec -i mysql_container mysql -u root -p springboot3wd7d5i4 < db/springboot3wd7d5i4.sql
```

---

## 最佳实践

### 1️⃣ 分支策略

```
main (生产分支)
  ↑
  |—— release/v1.0 (发布分支)
  |
develop (开发分支)
  ↑
  |—— feature/ai-chat (功能分支)
  |—— feature/analytics (功能分支)
```

**规则**：
- `main`：只接受来自 `release` 的合并，自动部署到生产
- `develop`：日常开发分支，自动构建测试
- `feature/*`：功能开发，提交 PR 到 `develop`

### 2️⃣ 优化构建速度

```yaml
# 使用缓存
- uses: actions/cache@v3
  with:
    path: ~/.m2
    key: ${{ runner.os }}-m2-${{ hashFiles('**/pom.xml') }}

# 并行执行独立任务
jobs:
  test:
    runs-on: ubuntu-latest
  lint:
    runs-on: ubuntu-latest
  # 两个 job 同时运行
```

### 3️⃣ 环境变量管理

```yaml
# 不要硬编码敏感信息
env:
  REGISTRY: ghcr.io
  IMAGE_NAME: ${{ github.repository }}

# 使用 Secrets
${{ secrets.DB_PASSWORD }}
```

### 4️⃣ 健康检查

```yaml
# 部署后验证服务
- name: Health Check
  run: |
    sleep 30
    curl -f http://${{ secrets.DEPLOY_HOST }}:8080/actuator/health
```

### 5️⃣ 通知机制

```yaml
# 构建失败时发送通知
- name: Notify on Failure
  if: failure()
  uses: actions/github-script@v6
  with:
    script: |
      github.rest.issues.create({
        owner: context.repo.owner,
        repo: context.repo.repo,
        title: 'CI/CD Build Failed',
        body: `Build failed: ${context.serverUrl}/${context.repo.owner}/${context.repo.repo}/actions/runs/${context.runId}`
      })
```

### 6️⃣ 版本标签

```bash
# 创建发布版本
git tag -a v1.0.0 -m "Release version 1.0.0"
git push origin v1.0.0
```

在 workflow 中添加：
```yaml
on:
  push:
    tags:
      - 'v*'
```

### 7️⃣ 回滚策略

```bash
# 快速回滚到上一个版本
docker-compose down
docker-compose up -d ghcr.io/username/springboot-paper-topic:v0.9.0
```

---

## 📊 CI/CD 成熟度评估

| 级别 | 特征 | 状态 |
|------|------|------|
| L1 | 手动构建部署 | ❌ |
| L2 | 自动化构建 | ✅ |
| L3 | 自动化测试 | ✅ |
| L4 | 自动化部署 | ✅ |
| L5 | 蓝绿部署/金丝雀发布 | 🔜 |

---

## 🎓 学习资源

- [GitHub Actions 官方文档](https://docs.github.com/en/actions)
- [Docker 最佳实践](https://docs.docker.com/develop/dev-best-practices/)
- [Spring Boot 部署指南](https://spring.io/guides/gs/spring-boot-docker/)

---

**祝您 CI/CD 之旅顺利！** 🚀
