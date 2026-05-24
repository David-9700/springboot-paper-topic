# AI API 集成指南

## 📋 概述

本系统已集成两个主流 AI API：
1. **DeepSeek** - 高性能对话 AI
2. **百度文心一言** - 中文理解能力强

您可以根据需求选择使用其中一个。

---

## 🔑 获取 API Key

### 1. DeepSeek API

#### 注册步骤
1. 访问 [DeepSeek 官网](https://platform.deepseek.com/)
2. 注册账号并登录
3. 进入"API Keys"页面
4. 创建新的 API Key
5. 复制保存 API Key

#### 定价
- 免费额度：新用户有一定免费额度
- 付费套餐：按使用量计费，价格实惠

#### API 信息
- **API URL**: `https://api.deepseek.com/v1/chat/completions`
- **模型**: `deepseek-chat`
- **文档**: https://platform.deepseek.com/api-docs/

---

### 2. 百度文心一言 API

#### 注册步骤
1. 访问 [百度智能云](https://cloud.baidu.com/)
2. 注册账号并实名认证
3. 进入"控制台" → "产品服务" → "自然语言处理"
4. 创建应用
5. 获取 API Key 和 Secret Key

#### 定价
- 免费额度：有一定的免费调用次数
- 付费套餐：按 QPS 和调用量计费

#### API 信息
- **API URL**: `https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions`
- **模型**: `ernie-bot`
- **文档**: https://cloud.baidu.com/doc/WENXINWORKSHOP/s/

---

## ⚙️ 配置方法

### 方法一：修改 application.yml（推荐）

编辑 `src/main/resources/application.yml`：

```yaml
ai:
  # 选择使用的 AI 提供商: deepseek 或 wenxin
  provider: deepseek
  
  # DeepSeek 配置
  deepseek:
    api-key: sk-your-deepseek-api-key-here
    api-url: https://api.deepseek.com/v1/chat/completions
    model: deepseek-chat
    temperature: 0.7
    max-tokens: 2000
  
  # 文心一言配置（如果使用文心一言）
  wenxin:
    api-key: your-wenxin-api-key
    secret-key: your-wenxin-secret-key
    api-url: https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions
    model: ernie-bot
    temperature: 0.8
    max-tokens: 2000
```

### 方法二：使用环境变量

设置环境变量：

**Linux/Mac:**
```bash
export DEEPSEEK_API_KEY=sk-your-api-key
export WENXIN_API_KEY=your-wenxin-api-key
export WENXIN_SECRET_KEY=your-wenxin-secret-key
```

**Windows:**
```cmd
set DEEPSEEK_API_KEY=sk-your-api-key
set WENXIN_API_KEY=your-wenxin-api-key
set WENXIN_SECRET_KEY=your-wenxin-secret-key
```

**Docker:**
在 `.env` 文件中配置：
```env
DEEPSEEK_API_KEY=sk-your-api-key
WENXIN_API_KEY=your-wenxin-api-key
WENXIN_SECRET_KEY=your-wenxin-secret-key
```

---

## 🧪 测试配置

### 1. 启动应用

```bash
# 本地运行
./start.sh  # Linux/Mac
start.bat   # Windows

# Docker 运行
docker-compose up -d
```

### 2. 访问聊天界面

打开浏览器访问：
```
http://localhost:8080/springboot3wd7d5i4/ai-chat.html
```

### 3. 测试对话

发送测试消息：
- "你好"
- "如何选择合适的课题？"
- "推荐人工智能方向的课题"

### 4. 查看日志

检查应用日志确认 API 调用是否成功：

```bash
# Docker 日志
docker-compose logs -f app

# 本地运行日志
tail -f logs/application.log
```

---

## 🔄 切换 AI 提供商

只需修改 `application.yml` 中的 `provider` 字段：

```yaml
ai:
  provider: wenxin  # 从 deepseek 改为 wenxin
```

重启应用即可生效。

---

## 📊 参数说明

### DeepSeek 参数

| 参数 | 说明 | 默认值 | 建议范围 |
|------|------|--------|----------|
| temperature | 随机性，越高越有创意 | 0.7 | 0.0-1.0 |
| max_tokens | 最大回复长度 | 2000 | 100-4000 |
| model | 模型名称 | deepseek-chat | - |

### 文心一言参数

| 参数 | 说明 | 默认值 | 建议范围 |
|------|------|--------|----------|
| temperature | 随机性，越高越有创意 | 0.8 | 0.0-1.0 |
| max_tokens | 最大回复长度 | 2000 | 100-4000 |
| model | 模型名称 | ernie-bot | - |

---

## ❗ 常见问题

### Q1: API 调用失败怎么办？

**检查清单：**
1. ✅ API Key 是否正确
2. ✅ 网络连接是否正常
3. ✅ API 配额是否用完
4. ✅ 防火墙是否阻止请求

**解决方法：**
```bash
# 测试 API 连接
curl -X POST https://api.deepseek.com/v1/chat/completions \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_API_KEY" \
  -d '{"model":"deepseek-chat","messages":[{"role":"user","content":"你好"}]}'
```

### Q2: 响应速度慢？

**优化建议：**
1. 降低 `max_tokens` 值
2. 使用更快的网络
3. 考虑切换到国内 API（文心一言）

### Q3: 如何选择 DeepSeek 还是文心一言？

**对比：**

| 特性 | DeepSeek | 文心一言 |
|------|----------|----------|
| 中文理解 | ⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ |
| 英文能力 | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐ |
| 响应速度 | ⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ |
| 价格 | 较便宜 | 有免费额度 |
| 稳定性 | ⭐⭐⭐⭐ | ⭐⭐⭐⭐⭐ |

**建议：**
- 主要中文场景 → 文心一言
- 需要多语言支持 → DeepSeek
- 追求性价比 → DeepSeek
- 追求稳定性 → 文心一言

### Q4: 如何查看 API 使用情况？

**DeepSeek:**
- 登录 https://platform.deepseek.com/
- 查看"用量统计"

**文心一言:**
- 登录百度智能云控制台
- 查看"资源监控"

---

## 🔐 安全建议

1. **不要硬编码 API Key**
   - ❌ 不要在代码中直接写 API Key
   - ✅ 使用环境变量或配置文件

2. **定期轮换 API Key**
   - 建议每 3-6 个月更换一次

3. **限制 API 使用量**
   - 设置每日/每月预算上限

4. **监控异常使用**
   - 定期检查 API 调用日志
   - 设置告警通知

---

## 📞 技术支持

如遇到问题：
1. 查看应用日志
2. 检查 API 官方文档
3. 提交 GitHub Issue

---

## 🎯 下一步

配置完成后：
1. ✅ 测试聊天功能
2. ✅ 查看数据大屏
3. ✅ 体验智能推荐
4. ✅ 根据实际需求调整参数

**祝您使用愉快！** 🎉
