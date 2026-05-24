@echo off
echo ========================================
echo   Paper Topic Selection System
echo ========================================
echo.

echo [1/3] Checking Java environment...
java -version
if errorlevel 1 (
    echo Error: Java not found. Please install JDK 8 or higher.
    pause
    exit /b 1
)
echo.

echo [2/3] Checking Maven environment...
mvn -version
if errorlevel 1 (
    echo Error: Maven not found. Please install Maven.
    pause
    exit /b 1
)
echo.

echo [3/3] Starting application...
echo Note: Please ensure MySQL database is running and configured correctly.
echo.

mvn clean spring-boot:run

pause
