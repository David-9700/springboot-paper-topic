@echo off
chcp 65001 >nul
echo ========================================
echo   GitHub CI/CD 一键推送脚本
echo ========================================
echo.

cd /d %~dp0

REM 检查 Git 状态
echo 📊 检查 Git 状态...
git status
echo.

REM 显示待推送的提交
echo 📝 待推送的提交:
git log origin/main..HEAD --oneline
echo.

if errorlevel 1 (
    echo ❌ 没有待推送的提交
    pause
    exit /b 0
)

echo ========================================
echo   准备推送到 GitHub
echo ========================================
echo.
echo 📤 仓库地址: https://github.com/David-9700/springboot-paper-topic
echo.
echo ⚠️  如果推送失败，请尝试:
echo   1. 检查网络连接
echo   2. 使用手机热点
echo   3. 使用 GitHub Desktop
echo.

set /p CONFIRM="确认推送？(y/n): "
if /i not "%CONFIRM%"=="y" (
    echo ❌ 已取消
    pause
    exit /b 0
)

echo.
echo 🚀 正在推送...
echo.

git push origin main

if errorlevel 1 (
    echo.
    echo ❌ 推送失败！
    echo.
    echo 💡 解决方案:
    echo.
    echo   方案 1: 使用 GitHub Desktop (推荐)
    echo     1. 下载: https://desktop.github.com/
    echo     2. 添加仓库: D:\project\springboot3wd7d5i4
    echo     3. 点击 "Push origin"
    echo.
    echo   方案 2: 更换网络后重试
    echo     - 使用手机热点
    echo     - 或稍后再试
    echo.
    echo   方案 3: 使用代理
    echo     git config --global http.proxy http://127.0.0.1:1080
    echo     git push origin main
    echo     git config --global --unset http.proxy
    echo.
) else (
    echo.
    echo ✅ 推送成功！
    echo.
    echo 🎉 CI/CD 配置已完成！
    echo.
    echo 📊 查看 CI/CD 状态:
    echo    https://github.com/David-9700/springboot-paper-topic/actions
    echo.
    echo ⏱️  预计 8-12 分钟完成所有阶段
    echo.
)

pause
