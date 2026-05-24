@echo off
REM API Test Script (Windows Version)
REM Usage: test-api.bat

set BASE_URL=http://localhost:8080/springboot3wd7d5i4

echo ==========================================
echo   Paper Topic System - API Test
echo ==========================================
echo.

echo Checking if application is running...
curl -s %BASE_URL%/actuator/health >nul 2>&1
if errorlevel 1 (
    echo Error: Application is not running. Please start the application first.
    pause
    exit /b 1
)
echo OK - Application is ready
echo.

echo ==========================================
echo   Starting API Tests
echo ==========================================
echo.

echo Test 1: Health Check
curl -s %BASE_URL%/actuator/health
echo.
echo.

echo Test 2: AI Chat - Send Message
curl -s -X POST %BASE_URL%/aiagent/chat/send ^
  -H "Content-Type: application/json" ^
  -d "{\"userId\":1,\"userType\":\"student\",\"message\":\"How to choose a topic?\"}"
echo.
echo.

echo Test 3: AI Recommendation - Generate Topics
curl -s -X POST %BASE_URL%/aiagent/recommendation/generate/topics?studentId=1
echo.
echo.

echo Test 4: Analytics - System Overview
curl -s %BASE_URL%/analytics/system/overview
echo.
echo.

echo Test 5: Analytics - Dashboard
curl -s %BASE_URL%/analytics/dashboard
echo.
echo.

echo ==========================================
echo   Tests Completed
echo ==========================================
pause
