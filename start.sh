#!/bin/bash

echo "========================================"
echo "  论文选题管理系统 - 本地启动脚本"
echo "========================================"
echo ""

echo "[1/3] 检查 Java 环境..."
java -version
if [ $? -ne 0 ]; then
    echo "错误: 未检测到 Java 环境，请先安装 JDK 8 或更高版本"
    exit 1
fi
echo ""

echo "[2/3] 检查 Maven 环境..."
mvn -version
if [ $? -ne 0 ]; then
    echo "错误: 未检测到 Maven，请先安装 Maven"
    exit 1
fi
echo ""

echo "[3/3] 启动应用程序..."
echo "提示: 请确保 MySQL 数据库已启动并配置正确"
echo ""

mvn clean spring-boot:run
