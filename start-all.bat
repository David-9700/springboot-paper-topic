@echo off
chcp 65001 >nul
echo ========================================
echo   毕业论文选题管理系统 - 启动脚本
echo ========================================
echo.

REM 检查 Java 是否安装
java -version >nul 2>&1
if errorlevel 1 (
    echo ❌ 错误: 未检测到 Java，请先安装 JDK 8 或更高版本
    pause
    exit /b 1
)

echo ✅ Java 已安装
echo.

REM 设置项目路径
set PROJECT_DIR=%~dp0
cd /d %PROJECT_DIR%

echo 📦 正在编译后端项目...
echo.

REM 尝试使用 mvn 命令
where mvn >nul 2>&1
if errorlevel 1 (
    echo ⚠️  未找到 Maven 命令，请手动编译项目或使用 IDE 运行
    echo.
    echo 您可以选择以下方式启动：
    echo   1. 在 IDEA 中直接运行 SpringbootSchemaApplication.java
    echo   2. 安装 Maven 并配置环境变量
    echo   3. 使用已编译的 JAR 文件（如果存在）
    echo.
    
    REM 检查是否有已编译的 JAR
    if exist target\*.jar (
        echo ✅ 找到已编译的 JAR 文件，正在启动...
        for %%f in (target\*.jar) do (
            java -jar "%%f"
        )
    ) else (
        echo ❌ 未找到可执行的 JAR 文件
        pause
        exit /b 1
    )
) else (
    echo ✅ Maven 已安装，开始编译...
    call mvn clean package -DskipTests
    
    if errorlevel 1 (
        echo ❌ 编译失败，请检查错误信息
        pause
        exit /b 1
    )
    
    echo.
    echo ✅ 编译成功！正在启动后端服务...
    echo.
    
    for %%f in (target\*.jar) do (
        start "后端服务" java -jar "%%f"
    )
)

echo.
echo ⏳ 等待后端服务启动...
timeout /t 10 /nobreak >nul

echo.
echo 🚀 正在启动前端服务...
echo.

cd src\main\resources\admin\admin

REM 检查 node_modules 是否存在
if not exist node_modules (
    echo 📦 首次运行，正在安装前端依赖...
    call npm install
)

echo.
echo 🌐 启动前端开发服务器...
echo.

start "前端服务" cmd /k "npm run serve"

echo.
echo ========================================
echo   启动完成！
echo ========================================
echo.
echo 📍 访问地址：
echo   前端管理端: http://localhost:8081
echo   后端 API:   http://localhost:8080/springboot3wd7d5i4
echo.
echo 💡 提示：
echo   - 两个窗口已在新标签页中打开
echo   - 按 Ctrl+C 可停止服务
echo   - 查看日志窗口了解详细状态
echo.
pause
