# 🚀 AI 功能快速开始指南

## ✨ 新功能概览

本次更新为您带来了：
1. ✅ **真实 AI API 集成** - DeepSeek 和文心一言
2. ✅ **AI 聊天界面** - 美观的网页聊天应用
3. ✅ **数据可视化大屏** - ECharts 图表展示
4. ✅ **智能推荐增强** - AI 驱动的个性化推荐

---

## 📋 前置准备

### 1. 获取 AI API Key（二选一）

#### 选项 A: DeepSeek（推荐）
- 注册地址: https://platform.deepseek.com/
- 优点: 性能好、价格实惠
- 免费额度: 新用户有免费试用

#### 选项 B: 百度文心一言
- 注册地址: https://cloud.baidu.com/
- 优点: 中文理解强、国内稳定
- 免费额度: 有一定免费调用次数

---

## ⚡ 5分钟快速启动

### 步骤 1: 配置 AI API Key

编辑 `src/main/resources/application.yml`：

```yaml
ai:
  provider: deepseek  # 或 wenxin
  
  deepseek:
    api-key: sk-your-api-key-here  # 替换为你的 API Key
```

### 步骤 2: 启动应用

```bash
# 方式一：Docker（推荐）
docker-compose up -d

# 方式二：本地运行
./start.sh  # Linux/Mac
start.bat   # Windows
```

### 步骤 3: 访问前端界面

打开浏览器访问：

**AI 聊天界面:**
```
http://localhost:8080/springboot3wd7d5i4/ai-chat.html
```

**数据可视化大屏:**
```
http://localhost:8080/springboot3wd7d5i4/dashboard.html
```

---

## 💬 使用 AI 聊天

### 功能特性
- ✅ 多轮对话，支持上下文
- ✅ 会话历史自动保存
- ✅ 快捷建议按钮
- ✅ 实时打字指示器
- ✅ 一键清除会话

### 示例对话

**用户**: 如何选择合适的课题？

**AI**: 选择课题时建议考虑以下几点：

1. **兴趣导向** - 选择你真正感兴趣的方向
2. **专业匹配** - 符合你的专业背景
3. **可行性** - 确保有足够的数据和资源
4. **创新性** - 有一定的研究价值
5. **导师资源** - 有相关领域的指导教师

你可以告诉我你的专业和兴趣方向，我可以为你推荐更具体的课题。

---

## 📊 查看数据大屏

### 展示内容
- 📈 系统概览统计卡片
- 📚 课题分类饼图
- 🏫 学院学生分布柱状图
- 📉 选题趋势折线图
- 👨‍🏫 教师指导学生排行
- 📋 开题报告通过率

### 实时更新
数据大屏会自动从后端获取最新统计数据，刷新页面即可更新。

---

## 🔧 高级配置

### 切换 AI 提供商

在 `application.yml` 中修改：

```yaml
ai:
  provider: wenxin  # 从 deepseek 改为 wenxin
  
  wenxin:
    api-key: your-wenxin-api-key
    secret-key: your-wenxin-secret-key
```

### 调整 AI 参数

```yaml
ai:
  deepseek:
    temperature: 0.7      # 随机性 (0.0-1.0)
    max-tokens: 2000      # 最大回复长度
```

**参数说明：**
- `temperature`: 越高越有创意，越低越保守
- `max_tokens`: 控制回复长度，影响响应速度

---

## 🧪 测试 API

### 使用 cURL 测试

```bash
# 测试 AI 聊天
curl -X POST http://localhost:8080/springboot3wd7d5i4/aiagent/chat/send \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "userType": "student",
    "message": "你好"
  }'

# 测试数据大屏
curl http://localhost:8080/springboot3wd7d5i4/analytics/dashboard
```

### 使用测试脚本

```bash
# Linux/Mac
chmod +x test-api.sh
./test-api.sh

# Windows
test-api.bat
```

---

## 🎨 前端界面定制

### 修改聊天界面样式

文件位置: `src/main/resources/static/ai-chat.html`

**修改主题颜色:**
```css
/* 找到这部分 CSS */
background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);

/* 改为你喜欢的颜色 */
background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
```

### 修改大屏图表

文件位置: `src/main/resources/static/dashboard.html`

**添加新图表:**
```javascript
// 在 charts-grid 中添加新的 chart-container
// 然后在 JavaScript 中创建对应的 ECharts 实例
```

---

## 📱 移动端适配

两个前端页面都已做响应式设计，可以在手机上正常访问：
- 聊天界面自适应屏幕宽度
- 数据大屏图表自动调整大小

---

## ❗ 常见问题

### Q1: 聊天界面无法发送消息？

**检查：**
1. 应用是否正常启动
2. API Key 是否配置正确
3. 浏览器控制台是否有错误

**解决：**
```bash
# 查看应用日志
docker-compose logs -f app

# 测试后端 API
curl http://localhost:8080/springboot3wd7d5i4/actuator/health
```

### Q2: 数据大屏显示空白？

**原因：** 数据库中还没有数据

**解决：**
```bash
# 导入示例数据
mysql -u root -p springboot3wd7d5i4 < db/springboot3wd7d5i4.sql
```

### Q3: AI 响应很慢？

**优化：**
1. 降低 `max_tokens` 值（如改为 1000）
2. 切换到更快的 API 提供商
3. 检查网络连接

### Q4: 如何清除聊天历史？

**方法一：** 点击聊天界面右上角的"清除会话"按钮

**方法二：** 浏览器控制台执行：
```javascript
localStorage.removeItem('chat_session_id');
location.reload();
```

---

## 🎯 下一步建议

### 立即可做
1. ✅ 体验 AI 聊天功能
2. ✅ 查看数据可视化大屏
3. ✅ 测试不同的 AI 提供商
4. ✅ 调整 AI 参数看效果

### 短期优化
1. 添加用户认证（从登录信息获取 userId）
2. 自定义聊天机器人人设
3. 添加更多图表类型
4. 实现实时数据更新（WebSocket）

### 长期规划
1. 训练专属模型
2. 添加语音交互
3. 实现多语言支持
4. 开发移动端 App

---

## 📚 相关文档

- [AI API 配置详细指南](AI_API_GUIDE.md)
- [完整 API 文档](API_DOCUMENTATION.md)
- [部署手册](DEPLOYMENT.md)
- [项目总结](FINAL_REPORT.md)

---

## 🆘 获取帮助

遇到问题？

1. **查看日志**
   ```bash
   docker-compose logs -f app
   ```

2. **查阅文档**
   - AI_API_GUIDE.md
   - QUICKSTART.md

3. **提交 Issue**
   - GitHub Issues

---

## 🎉 完成！

现在你已经成功集成了真实的 AI API，并拥有了美观的前端界面！

**享受智能化的论文选题管理体验吧！** 🚀

---

**版本**: v2.1.0  
**更新**: 2024-01-15  
**新增**: DeepSeek + 文心一言 + 前端界面
