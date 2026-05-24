# Windows 快速启动指南

## ⚡ 3 步快速启动（如果已安装 Java 和 Maven）

### 步骤 1: 配置数据库

```cmd
mysql -u root -p
```

在 MySQL 中执行：
```sql
CREATE DATABASE IF NOT EXISTS springboot3wd7d5i4;
USE springboot3wd7d5i4;
source D:/project/springboot3wd7d5i4/db/springboot3wd7d5i4.sql;
source D:/project/springboot3wd7d5i4/db/ai_agent_tables.sql;
exit;
```

### 步骤 2: 修改配置

编辑 `src\main\resources\application.yml`，修改数据库密码：

```yaml
spring:
  datasource:
    password: 你的MySQL密码  # 改这里
```

### 步骤 3: 启动应用

```cmd
cd D:\project\springboot3wd7d5i4
start.bat
```

等待看到 "Started SpringbootSchemaApplication" 后，访问：
- http://localhost:8080/springboot3wd7d5i4/ai-chat.html
- http://localhost:8080/springboot3wd7d5i4/dashboard.html

---

## ❗ 如果遇到错误

### 错误 1: 'java' 不是内部或外部命令

**说明**: 没有安装 Java 或环境变量未配置

**解决**: 查看 [WINDOWS_SETUP.md](WINDOWS_SETUP.md) 详细安装指南

**快速安装**:
1. 下载 JDK 8: https://www.oracle.com/java/technologies/downloads/
2. 安装后配置环境变量
3. 重新打开命令行

### 错误 2: 'mvn' 不是内部或外部命令

**说明**: 没有安装 Maven 或环境变量未配置

**解决**: 查看 [WINDOWS_SETUP.md](WINDOWS_SETUP.md) 详细安装指南

**快速安装**:
1. 下载 Maven: https://maven.apache.org/download.cgi
2. 解压并配置环境变量
3. 重新打开命令行

### 错误 3: 中文乱码

**解决**: 在命令行执行
```cmd
chcp 65001
```

---

## 🎯 验证环境

```cmd
# 检查 Java
java -version

# 检查 Maven
mvn -version

# 检查 MySQL
mysql --version
```

如果都能显示版本信息，说明环境OK！

---

## 📞 需要帮助？

1. 查看完整文档: [WINDOWS_SETUP.md](WINDOWS_SETUP.md)
2. 检查日志文件: `target\logs\` 或控制台输出
3. 确认所有前置软件已安装

---

**祝你使用愉快！** 🚀
