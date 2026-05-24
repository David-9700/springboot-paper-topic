# Windows 环境设置指南

## ❗ 错误说明

如果你看到类似以下错误：
```
'mvn' 不是内部或外部命令，也不是可运行的程序或批处理文件。
'java' 不是内部或外部命令，也不是可运行的程序或批处理文件。
```

这说明你的 Windows 系统还没有安装 Java 和 Maven，或者没有配置环境变量。

---

## 📋 前置要求

需要安装以下软件：
1. ✅ JDK 8 或更高版本
2. ✅ Maven 3.6+
3. ✅ MySQL 8.0+
4. ✅ Git（可选）

---

## 🔧 安装步骤

### 1. 安装 JDK

#### 下载
访问 Oracle 官网或 AdoptOpenJDK：
- Oracle JDK: https://www.oracle.com/java/technologies/downloads/
- AdoptOpenJDK: https://adoptopenjdk.net/

推荐下载：**JDK 8** 或 **JDK 11**

#### 安装
1. 运行安装程序
2. 记住安装路径（例如：`C:\Program Files\Java\jdk1.8.0_xxx`）

#### 配置环境变量
1. 右键"此电脑" → "属性" → "高级系统设置"
2. 点击"环境变量"
3. 在"系统变量"中新建：
   - 变量名：`JAVA_HOME`
   - 变量值：`C:\Program Files\Java\jdk1.8.0_xxx`（你的 JDK 安装路径）

4. 编辑 `Path` 变量，添加：
   ```
   %JAVA_HOME%\bin
   ```

#### 验证
打开新的命令行窗口（CMD），输入：
```cmd
java -version
javac -version
```

应该显示 Java 版本信息。

---

### 2. 安装 Maven

#### 下载
访问 Maven 官网：https://maven.apache.org/download.cgi

下载：`apache-maven-3.x.x-bin.zip`

#### 安装
1. 解压到某个目录（例如：`C:\Program Files\Apache\maven`）

#### 配置环境变量
1. 新建系统变量：
   - 变量名：`MAVEN_HOME`
   - 变量值：`C:\Program Files\Apache\maven`

2. 编辑 `Path` 变量，添加：
   ```
   %MAVEN_HOME%\bin
   ```

#### 验证
打开新的命令行窗口，输入：
```cmd
mvn -version
```

应该显示 Maven 版本信息。

---

### 3. 安装 MySQL

#### 下载
访问 MySQL 官网：https://dev.mysql.com/downloads/mysql/

下载：**MySQL Installer for Windows**

#### 安装
1. 运行安装程序
2. 选择"Developer Default"或"Server only"
3. 设置 root 密码（记住这个密码！）
4. 完成安装

#### 验证
打开命令行，输入：
```cmd
mysql -u root -p
```

输入密码后应该能进入 MySQL 命令行。

---

### 4. 初始化数据库

```cmd
# 登录 MySQL
mysql -u root -p

# 创建数据库
CREATE DATABASE IF NOT EXISTS springboot3wd7d5i4 
DEFAULT CHARACTER SET utf8mb4 
DEFAULT COLLATE utf8mb4_unicode_ci;

# 使用数据库
USE springboot3wd7d5i4;

# 导入数据（假设 SQL 文件在 D:\project\springboot3wd7d5i4\db）
source D:/project/springboot3wd7d5i4/db/springboot3wd7d5i4.sql;
source D:/project/springboot3wd7d5i4/db/ai_agent_tables.sql;

# 退出
exit;
```

---

### 5. 配置应用

编辑 `src\main\resources\application.yml`：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/springboot3wd7d5i4?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
    username: root
    password: 你的MySQL密码  # 修改这里
```

如果要使用 AI 功能，还需要配置 API Key：

```yaml
ai:
  provider: deepseek
  deepseek:
    api-key: sk-your-api-key-here  # 替换为你的 API Key
```

---

## 🚀 启动应用

### 方式一：使用启动脚本

```cmd
# 在项目根目录执行
start.bat
```

### 方式二：手动启动

```cmd
# 进入项目目录
cd D:\project\springboot3wd7d5i4

# 编译并运行
mvn clean spring-boot:run
```

### 方式三：打包后运行

```cmd
# 打包
mvn clean package -DskipTests

# 运行 JAR
java -jar target\springboot3wd7d5i4-0.0.1-SNAPSHOT.jar
```

---

## 🌐 访问应用

启动成功后，访问：

- **AI 聊天界面**: http://localhost:8080/springboot3wd7d5i4/ai-chat.html
- **数据大屏**: http://localhost:8080/springboot3wd7d5i4/dashboard.html
- **健康检查**: http://localhost:8080/springboot3wd7d5i4/actuator/health

---

## ❓ 常见问题

### Q1: 'java' 不是内部或外部命令

**原因**: Java 未安装或环境变量未配置

**解决**:
1. 确认已安装 JDK
2. 检查 `JAVA_HOME` 环境变量
3. 检查 `Path` 中是否有 `%JAVA_HOME%\bin`
4. **重新打开命令行窗口**（重要！）

### Q2: 'mvn' 不是内部或外部命令

**原因**: Maven 未安装或环境变量未配置

**解决**:
1. 确认已安装 Maven
2. 检查 `MAVEN_HOME` 环境变量
3. 检查 `Path` 中是否有 `%MAVEN_HOME%\bin`
4. **重新打开命令行窗口**

### Q3: 端口 8080 被占用

**解决**:
修改 `application.yml`：
```yaml
server:
  port: 8081  # 改为其他端口
```

### Q4: 数据库连接失败

**检查**:
1. MySQL 是否启动
2. 用户名密码是否正确
3. 数据库是否创建
4. 防火墙是否阻止

**测试连接**:
```cmd
mysql -u root -p
```

### Q5: 中文乱码

**解决**:
1. 确保文件编码为 UTF-8
2. 在命令行执行：
   ```cmd
   chcp 65001
   ```

---

## 🔍 验证安装

### 检查 Java
```cmd
java -version
```
应该显示类似：
```
java version "1.8.0_xxx"
Java(TM) SE Runtime Environment (build 1.8.0_xxx-bxx)
Java HotSpot(TM) 64-Bit Server VM (build 25.xxx-bxx, mixed mode)
```

### 检查 Maven
```cmd
mvn -version
```
应该显示类似：
```
Apache Maven 3.x.x
Maven home: C:\Program Files\Apache\maven
Java version: 1.8.0_xxx
```

### 检查 MySQL
```cmd
mysql --version
```
应该显示类似：
```
mysql  Ver 8.0.xx for Win64 on x86_64 (MySQL Community Server - GPL)
```

---

## 📝 快速检查清单

- [ ] JDK 已安装
- [ ] JAVA_HOME 环境变量已配置
- [ ] Path 中包含 %JAVA_HOME%\bin
- [ ] Maven 已安装
- [ ] MAVEN_HOME 环境变量已配置
- [ ] Path 中包含 %MAVEN_HOME%\bin
- [ ] MySQL 已安装并启动
- [ ] 数据库已创建
- [ ] SQL 文件已导入
- [ ] application.yml 已配置
- [ ] java -version 能正常显示
- [ ] mvn -version 能正常显示
- [ ] mysql -u root -p 能正常登录

---

## 🆘 获取帮助

如果仍然遇到问题：

1. **检查环境变量**
   ```cmd
   echo %JAVA_HOME%
   echo %MAVEN_HOME%
   echo %Path%
   ```

2. **查看完整错误信息**
   ```cmd
   start.bat > log.txt 2>&1
   ```
   然后查看 `log.txt` 文件

3. **重启电脑**
   有时候环境变量需要重启才能生效

4. **使用 PowerShell**
   如果 CMD 有问题，可以尝试 PowerShell

---

## ✅ 成功标志

当你看到以下输出时，说明环境配置成功：

```
========================================
  Paper Topic Selection System
========================================

[1/3] Checking Java environment...
java version "1.8.0_xxx"
...

[2/3] Checking Maven environment...
Apache Maven 3.x.x
...

[3/3] Starting application...
...
Started SpringbootSchemaApplication in xx.xxx seconds
```

**恭喜！环境配置完成！** 🎉
