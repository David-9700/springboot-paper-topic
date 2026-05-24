#!/bin/bash

# API 测试脚本
# 使用方法: ./test-api.sh

BASE_URL="http://localhost:8080/springboot3wd7d5i4"

echo "=========================================="
echo "  论文选题管理系统 - API 测试"
echo "=========================================="
echo ""

# 颜色定义
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# 测试计数器
PASS=0
FAIL=0

# 测试函数
test_api() {
    local name=$1
    local method=$2
    local endpoint=$3
    local data=$4
    
    echo -e "${YELLOW}测试: ${name}${NC}"
    
    if [ "$method" == "GET" ]; then
        response=$(curl -s -o /dev/null -w "%{http_code}" "${BASE_URL}${endpoint}")
    else
        response=$(curl -s -o /dev/null -w "%{http_code}" -X "${method}" \
            -H "Content-Type: application/json" \
            -d "${data}" \
            "${BASE_URL}${endpoint}")
    fi
    
    if [ "$response" == "200" ]; then
        echo -e "${GREEN}✓ 通过 (HTTP ${response})${NC}"
        ((PASS++))
    else
        echo -e "${RED}✗ 失败 (HTTP ${response})${NC}"
        ((FAIL++))
    fi
    echo ""
}

# 等待应用启动
echo "检查应用是否运行..."
until curl -s "${BASE_URL}/actuator/health" > /dev/null 2>&1; do
    echo "等待应用启动..."
    sleep 2
done
echo -e "${GREEN}✓ 应用已就绪${NC}"
echo ""

# 开始测试
echo "=========================================="
echo "  开始 API 测试"
echo "=========================================="
echo ""

# 1. 健康检查
test_api "健康检查" "GET" "/actuator/health" ""

# 2. AI 聊天功能
test_api "AI 聊天 - 发送消息" "POST" "/aiagent/chat/send" \
    '{"userId":1,"userType":"student","message":"如何选择合适的课题？"}'

test_api "AI 聊天 - 获取历史" "GET" "/aiagent/chat/history?sessionId=test-session" ""

# 3. AI 推荐功能
test_api "AI 推荐 - 生成课题推荐" "POST" "/aiagent/recommendation/generate/topics?studentId=1" ""

test_api "AI 推荐 - 生成导师推荐" "POST" "/aiagent/recommendation/generate/teachers?studentId=1" ""

test_api "AI 推荐 - 获取推荐列表" "GET" "/aiagent/recommendation/list?userId=1&userType=student" ""

# 4. 数据分析功能
test_api "数据分析 - 系统概览" "GET" "/analytics/system/overview" ""

test_api "数据分析 - 课题分类" "GET" "/analytics/topics/by-category" ""

test_api "数据分析 - 学院分布" "GET" "/analytics/students/by-college" ""

test_api "数据分析 - 选题趋势" "GET" "/analytics/topics/trend" ""

test_api "数据分析 - 教师统计" "GET" "/analytics/teachers/student-count" ""

test_api "数据分析 - 开题统计" "GET" "/analytics/kaiti/statistics" ""

test_api "数据分析 - Dashboard" "GET" "/analytics/dashboard" ""

# 测试结果汇总
echo "=========================================="
echo "  测试结果汇总"
echo "=========================================="
echo -e "${GREEN}通过: ${PASS}${NC}"
echo -e "${RED}失败: ${FAIL}${NC}"
echo "总计: $((PASS + FAIL))"
echo ""

if [ $FAIL -eq 0 ]; then
    echo -e "${GREEN}✓ 所有测试通过！${NC}"
    exit 0
else
    echo -e "${RED}✗ 部分测试失败，请检查应用状态和数据库连接${NC}"
    exit 1
fi
