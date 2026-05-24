# 数据库快速设置指南

## ❌ 错误信息

```
java.sql.SQLSyntaxErrorException: Unknown database 'xxx'
```

## ✅ 已修复

已将 `application.yml` 中的数据库名称从 `xxx` 改为 `springboot3wd7d5i4`。

---

## 📋 创建数据库步骤

### 方法一：使用 MySQL 命令行

```cmd
mysql -u root -p
```

输入密码后，执行以下 SQL：

```sql
-- 1. 创建数据库
CREATE DATABASE IF NOT EXISTS springboot3wd7d5i4 
DEFAULT CHARACTER SET utf8mb4 
DEFAULT COLLATE utf8mb4_unicode_ci;

-- 2. 使用数据库
USE springboot3wd7d5i4;

-- 3. 导入基础数据
source D:/project/springboot3wd7d5i4/db/springboot3wd7d5i4.sql;

-- 4. 导入 AI Agent 表
source D:/project/springboot3wd7d5i4/db/ai_agent_tables.sql;

-- 5. 验证表是否创建成功
SHOW TABLES;

-- 6. 退出
exit;
```

### 方法二：使用 MySQL Workbench（图形界面）

1. 打开 MySQL Workbench
2. 连接到本地 MySQL
3. 点击 "Schema" 标签页
4. 右键 → "Create Schema"
5. 输入名称：`springboot3wd7d5i4`
6. Character Set: `utf8mb4`
7. Collation: `utf8mb4_unicode_ci`
8. 点击 "Apply"

然后导入 SQL 文件：
1. File → Open SQL Script
2. 选择 `db/springboot3wd7d5i4.sql`
3. 执行（闪电图标）
4. 同样导入 `db/ai_agent_tables.sql`

### 方法三：使用 Navicat 或其他工具

类似 MySQL Workbench 的操作步骤。

---

## 🔧 修改数据库密码（如果需要）

如果你的 MySQL root 密码不是 `123456`，需要修改配置：

编辑 `src\main\resources\application.yml`：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/springboot3wd7d5i4?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
    username: root
    password: 你的实际密码  # 改这里
```

或者使用环境变量：

```cmd
set DB_PASSWORD=你的实际密码
```

---

## ✅ 验证数据库

### 检查数据库是否存在

```cmd
mysql -u root -p -e "SHOW DATABASES;" | findstr springboot3wd7d5i4
```

应该能看到 `springboot3wd7d5i4`。

### 检查表是否创建

```cmd
mysql -u root -p springboot3wd7d5i4 -e "SHOW TABLES;"
```

应该看到所有表，包括：
- xuesheng（学生）
- jiaoshi（教师）
- ketixinxi（课题信息）
- ai_agent_chat（AI 聊天）
- ai_agent_recommendation（AI 推荐）
- 等等...

---

## 🚀 重新启动应用

数据库创建完成后，重新启动应用：

```cmd
cd D:\project\springboot3wd7d5i4
mvn clean spring-boot:run
```

或者：

```cmd
start.bat
```

这次应该能正常启动了！

---

## ❓ 常见问题

### Q1: 无法连接 MySQL

**错误**: `Communications link failure`

**解决**:
1. 确认 MySQL 服务已启动
   ```cmd
   net start MySQL80
   ```
2. 检查端口 3306 是否被占用
   ```cmd
   netstat -an | findstr 3306
   ```

### Q2: 访问被拒绝

**错误**: `Access denied for user 'root'@'localhost'`

**解决**:
1. 确认用户名密码正确
2. 检查 MySQL 用户权限
   ```sql
   SELECT user, host FROM mysql.user;
   ```

### Q3: SQL 文件导入失败

**可能原因**:
- 文件路径不正确
- 编码问题

**解决**:
```cmd
# 使用完整路径
mysql -u root -p springboot3wd7d5i4 < D:\project\springboot3wd7d5i4\db\springboot3wd7d5i4.sql

# 或者在 MySQL 中使用正斜杠
source D:/project/springboot3wd7d5i4/db/springboot3wd7d5i4.sql;
```

### Q4: 字符集问题

**解决**:
确保数据库使用 utf8mb4：
```sql
ALTER DATABASE springboot3wd7d5i4 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;
```

---

## 📊 数据库结构

### 主要表说明

| 表名 | 说明 |
|------|------|
| xuesheng | 学生信息 |
| jiaoshi | 教师信息 |
| yuanxi | 学院信息 |
| zhuanye | 专业信息 |
| ketifenlei | 课题分类 |
| ketixinxi | 课题信息 |
| xuantixinxi | 选题信息 |
| kaitibaogao | 开题报告 |
| kaitichengji | 开题成绩 |
| renwushu | 任务书 |
| messages | 消息通知 |
| news | 新闻公告 |
| ai_agent_chat | AI 聊天记录 ⭐新增 |
| ai_agent_recommendation | AI 推荐记录 ⭐新增 |

---

## 🎯 快速测试

数据库设置完成后，测试连接：

```cmd
mysql -u root -p springboot3wd7d5i4 -e "SELECT COUNT(*) FROM xuesheng;"
```

如果能显示学生数量，说明数据库正常！

---

## ✅ 完成检查清单

- [ ] MySQL 服务已启动
- [ ] 数据库 `springboot3wd7d5i4` 已创建
- [ ] 字符集设置为 utf8mb4
- [ ] SQL 文件已导入
- [ ] 表已创建成功
- [ ] application.yml 中数据库名称正确
- [ ] 数据库密码配置正确

---

**数据库设置完成！** 🎉

现在可以重新启动应用了。
