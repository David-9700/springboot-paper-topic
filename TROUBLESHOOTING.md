# 常见问题修复指南

## ❌ 错误：找不到符号 BaseDao

### 错误信息
```
D:\project\springboot3wd7d5i4\src\main\java\com\dao\AiAgentChatDao.java:10:41
java: 找不到符号
  符号: 类 BaseDao
```

### 原因
项目使用的是 **MyBatis Plus**，应该继承 `BaseMapper` 而不是 `BaseDao`。

### ✅ 已修复

已将以下文件中的 `BaseDao` 改为 `BaseMapper`：
- `AiAgentChatDao.java`
- `AiAgentRecommendationDao.java`

**修改前：**
```java
public interface AiAgentChatDao extends BaseDao<AiAgentChatEntity> {
```

**修改后：**
```java
import com.baomidou.mybatisplus.mapper.BaseMapper;

public interface AiAgentChatDao extends BaseMapper<AiAgentChatEntity> {
```

---

## 🔧 如何避免类似错误

### MyBatis Plus 的正确用法

#### 1. DAO 接口
```java
package com.dao;

import com.entity.YourEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface YourDao extends BaseMapper<YourEntity> {
    // 可以添加自定义方法
}
```

#### 2. Service 接口
```java
package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.YourEntity;

public interface YourService extends IService<YourEntity> {
    // 可以添加自定义方法
}
```

#### 3. Service 实现
```java
package com.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.YourDao;
import com.entity.YourEntity;
import com.service.YourService;
import org.springframework.stereotype.Service;

@Service("yourService")
public class YourServiceImpl extends ServiceImpl<YourDao, YourEntity> 
        implements YourService {
    // 实现自定义方法
}
```

---

## 📋 其他常见问题

### 问题 1: Maven 命令找不到

**错误**: `'mvn' 不是内部或外部命令`

**解决**: 
1. 安装 Maven
2. 配置环境变量 `MAVEN_HOME`
3. 在 Path 中添加 `%MAVEN_HOME%\bin`
4. 重新打开命令行

详见: [WINDOWS_SETUP.md](WINDOWS_SETUP.md)

---

### 问题 2: Java 命令找不到

**错误**: `'java' 不是内部或外部命令`

**解决**:
1. 安装 JDK 8 或更高版本
2. 配置环境变量 `JAVA_HOME`
3. 在 Path 中添加 `%JAVA_HOME%\bin`
4. 重新打开命令行

详见: [WINDOWS_SETUP.md](WINDOWS_SETUP.md)

---

### 问题 3: 端口被占用

**错误**: `Port 8080 was already in use`

**解决**:
修改 `application.yml`:
```yaml
server:
  port: 8081  # 改为其他可用端口
```

---

### 问题 4: 数据库连接失败

**错误**: `Communications link failure`

**检查清单**:
- [ ] MySQL 是否启动
- [ ] 数据库是否创建
- [ ] 用户名密码是否正确
- [ ] 防火墙是否阻止

**解决**:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/springboot3wd7d5i4
    username: root
    password: 你的密码  # 确认密码正确
```

---

### 问题 5: AI API 调用失败

**错误**: `DeepSeek API 请求失败` 或 `文心一言 API 请求失败`

**检查**:
1. API Key 是否正确配置
2. 网络连接是否正常
3. API 配额是否用完

**解决**:
```yaml
ai:
  provider: deepseek
  deepseek:
    api-key: sk-your-actual-api-key  # 确认 Key 正确
```

---

### 问题 6: 前端页面空白

**可能原因**:
1. 应用未启动
2. 路径错误
3. 浏览器缓存

**解决**:
1. 确认应用已启动并运行在 8080 端口
2. 清除浏览器缓存（Ctrl+F5）
3. 检查浏览器控制台错误信息

---

### 问题 7: 中文乱码

**解决**:
在命令行执行：
```cmd
chcp 65001
```

或者在 `application.yml` 中确保：
```yaml
server:
  tomcat:
    uri-encoding: UTF-8
```

---

## 🆘 获取帮助

如果遇到问题：

1. **查看完整日志**
   ```cmd
   mvn clean spring-boot:run > log.txt 2>&1
   ```
   然后查看 `log.txt`

2. **检查环境**
   ```cmd
   java -version
   mvn -version
   mysql --version
   ```

3. **查阅文档**
   - [WINDOWS_SETUP.md](WINDOWS_SETUP.md) - Windows 环境配置
   - [AI_QUICK_START.md](AI_QUICK_START.md) - AI 功能使用
   - [QUICKSTART.md](QUICKSTART.md) - 快速开始

4. **提交 Issue**
   - 描述具体问题
   - 附上错误日志
   - 说明操作步骤

---

## ✅ 验证修复

编译项目确认没有错误：

```cmd
cd D:\project\springboot3wd7d5i4
mvn clean compile
```

如果看到 `BUILD SUCCESS`，说明编译通过！

然后启动应用：
```cmd
mvn spring-boot:run
```

等待看到 "Started SpringbootSchemaApplication" 即表示启动成功。

---

**问题解决！** 🎉
