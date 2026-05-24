# Agentic AI API 接口文档

## 概述
本文档描述了论文选题管理系统中集成的 Agentic AI 功能接口。

## 基础信息
- **Base URL**: `http://localhost:8080/springboot3wd7d5i4`
- **认证方式**: 根据系统配置（部分接口需要登录）

---

## 1. AI 智能助手 (AI Agent Chat)

### 1.1 发送消息并获取 AI 回复
**接口**: `POST /aiagent/chat/send`

**请求体**:
```json
{
  "userId": 1,
  "userType": "student",
  "sessionId": "optional-session-id",
  "message": "如何选择合适的课题？"
}
```

**响应**:
```json
{
  "code": 0,
  "msg": "success",
  "data": {
    "sessionId": "generated-or-provided-session-id",
    "userMessage": { ... },
    "aiMessage": { ... }
  }
}
```

### 1.2 获取会话历史
**接口**: `GET /aiagent/chat/history`

**参数**:
- `sessionId`: 会话ID（必填）
- `page`: 页码，默认 1
- `limit`: 每页数量，默认 20

**响应**:
```json
{
  "code": 0,
  "msg": "success",
  "page": {
    "currPage": 1,
    "pageSize": 20,
    "totalPage": 1,
    "totalCount": 10,
    "list": [ ... ]
  }
}
```

### 1.3 清除会话
**接口**: `DELETE /aiagent/chat/session/{sessionId}`

**响应**:
```json
{
  "code": 0,
  "msg": "success"
}
```

---

## 2. AI 智能推荐 (AI Agent Recommendation)

### 2.1 生成课题推荐
**接口**: `POST /aiagent/recommendation/generate/topics`

**参数**:
- `studentId`: 学生ID

**响应**:
```json
{
  "code": 0,
  "msg": "success",
  "recommendations": [
    {
      "id": 1,
      "userId": 1,
      "recommendationType": "topic",
      "itemId": 123,
      "score": 0.95,
      "reason": "基于您的专业背景和兴趣匹配",
      "isRead": 0
    }
  ]
}
```

### 2.2 生成导师推荐
**接口**: `POST /aiagent/recommendation/generate/teachers`

**参数**:
- `studentId`: 学生ID

**响应**:
```json
{
  "code": 0,
  "msg": "success",
  "recommendations": [ ... ]
}
```

### 2.3 获取用户推荐列表
**接口**: `GET /aiagent/recommendation/list`

**参数**:
- `userId`: 用户ID
- `userType`: 用户类型 (student/teacher)
- `page`: 页码，默认 1
- `limit`: 每页数量，默认 10

**响应**:
```json
{
  "code": 0,
  "msg": "success",
  "page": { ... }
}
```

### 2.4 标记推荐为已读
**接口**: `POST /aiagent/recommendation/markRead/{id}`

**响应**:
```json
{
  "code": 0,
  "msg": "success"
}
```

---

## 3. 数据分析 (Analytics)

### 3.1 系统概览统计
**接口**: `GET /analytics/system/overview`

**响应**:
```json
{
  "code": 0,
  "msg": "success",
  "data": {
    "studentCount": 150,
    "teacherCount": 30,
    "topicCount": 80,
    "selectedCount": 60,
    "pendingCount": 20
  }
}
```

### 3.2 课题分类统计
**接口**: `GET /analytics/topics/by-category`

**响应**:
```json
{
  "code": 0,
  "msg": "success",
  "data": [
    {
      "ketifenlei": "人工智能",
      "count": 25
    }
  ]
}
```

### 3.3 学院学生分布
**接口**: `GET /analytics/students/by-college`

**响应**:
```json
{
  "code": 0,
  "msg": "success",
  "data": [ ... ]
}
```

### 3.4 选题趋势（按月）
**接口**: `GET /analytics/topics/trend`

**响应**:
```json
{
  "code": 0,
  "msg": "success",
  "data": [
    {
      "month": "2024-01",
      "count": 15
    }
  ]
}
```

### 3.5 教师指导学生统计
**接口**: `GET /analytics/teachers/student-count`

**响应**:
```json
{
  "code": 0,
  "msg": "success",
  "data": [
    {
      "jiaoshixingming": "张老师",
      "studentCount": 10
    }
  ]
}
```

### 3.6 开题报告统计
**接口**: `GET /analytics/kaiti/statistics`

**响应**:
```json
{
  "code": 0,
  "msg": "success",
  "data": {
    "submitted": 50,
    "approved": 45,
    "approvalRate": 90.0
  }
}
```

### 3.7 Dashboard 数据汇总
**接口**: `GET /analytics/dashboard`

**说明**: 一次性获取所有统计数据，适合首页展示

**响应**:
```json
{
  "code": 0,
  "msg": "success",
  "data": {
    "overview": { ... },
    "topicCategories": [ ... ],
    "collegeDistribution": [ ... ],
    "selectionTrend": [ ... ],
    "kaitiStats": { ... }
  }
}
```

---

## 使用示例

### JavaScript (Fetch API)
```javascript
// 发送 AI 聊天消息
fetch('http://localhost:8080/springboot3wd7d5i4/aiagent/chat/send', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
  },
  body: JSON.stringify({
    userId: 1,
    userType: 'student',
    message: '我想了解人工智能方向的课题'
  })
})
.then(response => response.json())
.then(data => console.log(data));

// 获取系统统计
fetch('http://localhost:8080/springboot3wd7d5i4/analytics/dashboard')
  .then(response => response.json())
  .then(data => console.log(data));
```

### Python (requests)
```python
import requests

base_url = "http://localhost:8080/springboot3wd7d5i4"

# 发送 AI 聊天消息
response = requests.post(f"{base_url}/aiagent/chat/send", json={
    "userId": 1,
    "userType": "student",
    "message": "如何写开题报告？"
})
print(response.json())

# 生成课题推荐
response = requests.post(f"{base_url}/aiagent/recommendation/generate/topics", 
                        params={"studentId": 1})
print(response.json())
```

---

## 注意事项

1. **数据库初始化**: 首次使用前需执行 `db/ai_agent_tables.sql` 创建相关表
2. **AI 集成**: 当前使用简单的关键词匹配，生产环境建议接入真实 AI API（如 OpenAI、文心一言等）
3. **性能优化**: 推荐生成逻辑可根据实际需求优化，添加缓存机制
4. **安全考虑**: 生产环境需要添加适当的权限控制和输入验证
