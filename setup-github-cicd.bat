@echo off
chcp 65001 >nul
echo ========================================
echo   GitHub CI/CD 一键配置脚本
echo ========================================
echo.

cd /d %~dp0

REM 检查 Git 是否安装
where git >nul 2>&1
if errorlevel 1 (
    echo ❌ 错误: 未检测到 Git，请先安装 Git
    echo 下载地址: https://git-scm.com/download/win
    pause
    exit /b 1
)

echo ✅ Git 已安装
echo.

REM 检查是否已经是 Git 仓库
if exist .git (
    echo ✅ Git 仓库已初始化
) else (
    echo 📦 初始化 Git 仓库...
    git init
    echo.
)

REM 添加远程仓库
echo 🔗 配置远程仓库...
git remote remove origin 2>nul
git remote add origin https://github.com/David-9700/springboot-paper-topic.git
echo ✅ 远程仓库配置完成
echo.

REM 添加所有文件
echo 📝 添加文件到 Git...
git add .
echo.

REM 提交
echo 💾 提交更改...
git commit -m "Add complete CI/CD configuration with Docker support"
if errorlevel 1 (
    echo ⚠️  没有需要提交的更改或提交失败
)
echo.

REM 切换到 main 分支
echo 🌿 切换到 main 分支...
git branch -M main
echo.

echo ========================================
echo   准备推送到 GitHub
echo ========================================
echo.
echo 📤 即将推送代码到:
echo    https://github.com/David-9700/springboot-paper-topic
echo.
echo ⚠️  请确保:
echo   1. 您有该仓库的推送权限
echo   2. 已配置 GitHub 认证（用户名/密码或 SSH）
echo.

set /p CONFIRM="确认推送？(y/n): "
if /i not "%CONFIRM%"=="y" (
    echo ❌ 已取消推送
    pause
    exit /b 0
)

echo.
echo 🚀 正在推送到 GitHub...
git push -u origin main

if errorlevel 1 (
    echo.
    echo ❌ 推送失败！
    echo.
    echo 可能的原因:
    echo   1. 仓库不存在 - 请在 GitHub 上创建 springboot-paper-topic 仓库
    echo   2. 认证失败 - 请配置 Git 凭证
    echo   3. 网络问题 - 请检查网络连接
    echo.
    echo 💡 解决方案:
    echo   创建仓库: https://github.com/new
    echo   仓库名称: springboot-paper-topic
    echo.
) else (
    echo.
    echo ✅ 推送成功！
    echo.
    echo 🎉 CI/CD 配置已完成！
    echo.
    echo 📊 下一步:
    echo   1. 访问 GitHub Actions 查看构建状态:
    echo      https://github.com/David-9700/springboot-paper-topic/actions
    echo.
    echo   2. 配置 GitHub Secrets (如果需要部署):
    echo      - DEPLOY_HOST: 服务器IP
    echo      - DEPLOY_USER: SSH用户名
    echo      - DEPLOY_SSH_KEY: SSH私钥
    echo.
    echo   3. 查看详细文档:
    echo      - GitHub_CICD_配置步骤.md
    echo      - GitHub_CICD_完全教程.md
    echo.
)

pause
