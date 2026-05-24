# 🚀 GitHub CI/CD 一键配置指南

## ✅ 已完成的配置

我已经为您创建了以下文件：

1. ✅ **Dockerfile** - Docker 镜像构建文件
2. ✅ **docker-compose.yml** - Docker Compose 部署配置
3. ✅ **.env.example** - 环境变量模板
4. ✅ **.github/workflows/maven.yml** - CI/CD 工作流（已存在）

---

## 📋 配置步骤（5步完成）

### 步骤 1：初始化 Git 仓库 ⚡

在 PowerShell 中执行：

```powershell
cd D:\project\springboot3wd7d5i4

# 初始化 Git（如果还没有）
git init

# 添加所有文件
git add .

# 提交
git commit -m "Add CI/CD configuration with Docker support"

# 关联远程仓库
git remote add origin https://github.com/David-9700/springboot-paper-topic.git

# 推送到 GitHub
git branch -M main
git push -u origin main
```

**执行后效果**：代码将上传到您的 GitHub 仓库

---

### 步骤 2：配置 GitHub Secrets 🔐

#### 2.1 访问 Secrets 设置页面

1. 打开浏览器访问：https://github.com/David-9700/springboot-paper-topic/settings/secrets/actions
2. 点击 **"New repository secret"**

#### 2.2 添加必需的 Secrets

根据您的 `maven.yml` 配置，需要添加以下 Secrets：

| Secret 名称 | 值 | 说明 |
|------------|-----|------|
| `DEPLOY_HOST` | 您的服务器IP | 如果没有服务器，可以跳过部署阶段 |
| `DEPLOY_USER` | root | SSH 登录用户名 |
| `DEPLOY_SSH_KEY` | SSH 私钥内容 | SSH 登录密钥 |

**⚠️ 重要提示**：如果您还没有生产服务器，CI/CD 的前两个阶段（Build & Test、Security Scan）仍然会正常运行，只是不会执行部署。

#### 2.3 快速生成 SSH 密钥（如果需要部署）

```bash
# 在本地生成密钥对
ssh-keygen -t ed25519 -C "github-actions@deploy" -f ~/.ssh/github_deploy

# 查看公钥（添加到服务器的 authorized_keys）
cat ~/.ssh/github_deploy.pub

# 查看私钥（复制到 GitHub Secrets）
cat ~/.ssh/github_deploy
```

---

### 步骤 3：推送代码触发 CI/CD 🎯

```bash
# 确保所有文件都已提交
git add .
git commit -m "Complete CI/CD setup"
git push origin main
```

**推送后自动触发**：
1. 访问：https://github.com/David-9700/springboot-paper-topic/actions
2. 您会看到工作流正在运行
3. 点击查看详情

---

### 步骤 4：监控执行状态 📊

#### 预期流程（约 8-10 分钟）

```
✅ Job 1: build-and-test (约 3-4 分钟)
   ├─ Checkout Code
   ├─ Setup JDK 8
   ├─ Cache Maven packages
   ├─ Build Project
   ├─ Run Unit Tests
   └─ Generate Coverage Report

✅ Job 2: security-scan (约 2-3 分钟)
   ├─ Checkout Code
   └─ Dependency Check

✅ Job 3: deploy (约 3-4 分钟，仅 main 分支)
   ├─ Setup Docker Buildx
   ├─ Login to GHCR
   ├─ Build and push Docker image
   └─ Deploy to Server (SSH)
```

#### 查看实时日志

1. 进入 **Actions** 标签
2. 点击最新的工作流运行
3. 展开各个 Job 查看详细步骤

---

### 步骤 5：验证部署结果 ✨

#### 如果配置了服务器部署

```bash
# SSH 登录到服务器
ssh user@your-server.com

# 检查容器状态
docker ps

# 查看应用日志
docker logs springboot-app

# 测试服务
curl http://localhost:8080/springboot3wd7d5i4/
```

#### 如果没有服务器（仅构建镜像）

镜像会推送到 GitHub Container Registry：
```
https://github.com/David-9700?tab=packages
```

---

## 🔧 本地测试 Docker 构建

在推送之前，您可以在本地测试 Docker 构建：

### 前提条件

安装 Docker Desktop：https://www.docker.com/products/docker-desktop

### 构建并运行

```powershell
# 1. 先编译项目（需要 Maven）
mvn clean package -DskipTests

# 2. 构建 Docker 镜像
docker build -t springboot-paper-topic:latest .

# 3. 运行容器
docker-compose up -d

# 4. 查看状态
docker ps

# 5. 查看日志
docker logs -f springboot-app

# 6. 停止服务
docker-compose down
```

---

## 📝 常见问题解决

### ❌ 问题 1：Git 推送失败

**错误**：`remote: Repository not found`

**解决**：
```bash
# 确认远程仓库地址
git remote -v

# 重新添加
git remote set-url origin https://github.com/David-9700/springboot-paper-topic.git

# 再次推送
git push origin main
```

### ❌ 问题 2：CI/CD 没有触发

**原因**：可能推送到其他分支

**解决**：
```bash
# 确认当前分支
git branch

# 切换到 main 并推送
git checkout main
git push origin main
```

### ❌ 问题 3：Docker 构建失败

**错误**：`target/*.jar not found`

**解决**：
```bash
# 先编译项目
mvn clean package -DskipTests

# 确认 JAR 文件存在
ls target/*.jar

# 再构建 Docker
docker build -t springboot-paper-topic:latest .
```

### ❌ 问题 4：Maven 命令找不到

**解决**：使用 IDEA 编译
1. 在 IDEA 中打开项目
2. 右侧 Maven 面板
3. 双击 `Lifecycle → package`

---

## 🎯 简化版 CI/CD（无服务器部署）

如果您暂时没有生产服务器，可以修改 `.github/workflows/maven.yml`，注释掉部署部分：

```yaml
# 注释掉整个 deploy job
# deploy:
#   runs-on: ubuntu-latest
#   needs: [build-and-test, security-scan]
#   ...
```

这样 CI/CD 只执行构建和测试，不会尝试部署。

---

## 📊 CI/CD 成熟度路线图

```
当前状态: L3 (自动化测试)
目标状态: L4 (自动化部署)

进度：
✅ L1 - 手动构建部署
✅ L2 - 自动化构建
✅ L3 - 自动化测试
🔜 L4 - 自动化部署（需要配置服务器）
```

---

## 🚀 下一步行动清单

- [ ] **立即执行**：推送代码到 GitHub
- [ ] **配置 Secrets**：在 GitHub 设置中添加必要的密钥
- [ ] **监控构建**：查看 Actions 页面的执行状态
- [ ] **测试部署**：如果有服务器，验证部署结果
- [ ] **优化配置**：根据实际运行情况调整

---

## 📞 获取帮助

如果遇到问题：

1. 查看 GitHub Actions 日志
2. 检查本地 Docker 构建是否成功
3. 确认 Secrets 配置正确
4. 参考完整教程：`GitHub_CICD_完全教程.md`

---

**准备好了吗？开始执行步骤 1 吧！** 💪
