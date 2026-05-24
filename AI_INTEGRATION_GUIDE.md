# AI 功能集成指南

## 📋 概述

已成功将 AI 智能功能集成到系统的前后台页面中，包括：

1. **学生端（前台）** - AI 智能推荐
2. **管理员端（后台）** - 数据分析面板
3. **教师端（后台）** - AI 辅助工具

---

## 🎯 功能说明

### 1. 学生端 - AI 智能推荐

**位置**: 前台首页右下角悬浮按钮

**功能特性**:
- 🔮 **课题推荐**: 根据学生兴趣和专业背景推荐合适的课题
- 👨‍🏫 **导师推荐**: 推荐与学生研究方向匹配的教师
- 📚 **学习资源**: 推荐相关学习资料和文献

**使用方式**:
1. 登录学生账号
2. 访问前台页面: `http://localhost:8080/springboot3wd7d5i4/front/dist/index.html`
3. 点击右下角的 "AI推荐" 悬浮按钮
4. 选择推荐类型，点击"获取推荐"

**组件文件**: 
- `src/main/resources/front/front/src/components/AiRecommendation.vue`

---

### 2. 管理员端 - 数据分析面板

**位置**: 后台管理首页中央区域

**功能特性**:
- 📊 **统计卡片**: 显示学生、教师、课题等关键数据
- 🥧 **课题分类饼图**: 展示各类课题的分布情况
- 📈 **学院分布柱状图**: 各学院学生数量对比
- 📉 **选题趋势折线图**: 选题数量随时间的变化
- 🏆 **教师指导排行**: 教师指导学生数量排名
- 📝 **开题报告状态**: 开题报告的审核状态分布
- 💡 **AI 智能建议**: 基于数据分析自动生成优化建议

**使用方式**:
1. 登录管理员账号
2. 访问后台页面: `http://localhost:8080/springboot3wd7d5i4/admin/dist/index.html`
3. 在首页即可查看完整的数据分析面板

**组件文件**: 
- `src/main/resources/admin/admin/src/components/AdminDashboard.vue`

---

### 3. 教师端 - AI 辅助工具

**位置**: 后台管理首页右下角悬浮按钮（仅教师可见）

**功能特性**:

#### 📋 课题审核辅助
- 查看待审核的开题报告
- AI 自动评估课题质量
- 生成审核意见和改进建议

#### 👥 学生指导
- 查看自己指导的学生列表
- 监控每个学生的完成进度
- AI 生成个性化指导建议

#### 📊 成绩评估
- 批量评估学生表现
- AI 生成初步评分建议
- 提供评分依据说明

**使用方式**:
1. 登录教师账号
2. 访问后台页面
3. 点击右下角的 "AI助手" 悬浮按钮
4. 选择相应功能模块使用

**组件文件**: 
- `src/main/resources/admin/admin/src/components/TeacherAiTools.vue`

---

## 🔧 技术实现

### 前端技术栈

- **Vue.js 2.x**: 组件化开发
- **Element UI**: UI 组件库
- **ECharts 5.x**: 数据可视化图表
- **Axios**: HTTP 请求

### 后端 API 接口

所有 AI 功能调用以下后端接口：

```
POST /aiagent/chat/send          # AI 对话
POST /aiagent/recommend/get      # 获取推荐
GET  /analytics/dashboard        # 数据分析面板
POST /aiagent/assessment/batch   # 批量评估（教师）
```

### 数据存储

- **ai_agent_chat**: AI 聊天记录表
- **ai_agent_recommendation**: AI 推荐记录表

---

## 📦 部署步骤

### 1. 确保数据库已创建

执行 SQL 文件创建必要的表：

```bash
mysql -u root -p springboot3wd7d5i4 < db/ai_agent_tables.sql
```

### 2. 重新编译前端项目

#### 学生端（前台）

```bash
cd src/main/resources/front/front
npm install
npm run build
```

#### 管理员端（后台）

```bash
cd src/main/resources/admin/admin
npm install
npm run build
```

### 3. 启动后端服务

```bash
cd D:\project\springboot3wd7d5i4
mvn clean spring-boot:run
```

或使用启动脚本：

```bash
start.bat
```

### 4. 访问页面

- **学生端**: http://localhost:8080/springboot3wd7d5i4/front/dist/index.html
- **管理员端**: http://localhost:8080/springboot3wd7d5i4/admin/dist/index.html

---

## ⚙️ 配置说明

### AI API 配置

编辑 `src/main/resources/application.yml`:

```yaml
ai:
  provider: deepseek  # 或 wenxin
  
  deepseek:
    api-key: your-deepseek-api-key
    model: deepseek-chat
    temperature: 0.7
    max-tokens: 2000
```

### 数据库配置

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/springboot3wd7d5i4
    username: root
    password: your-password
```

---

## 🎨 自定义样式

### 修改主题颜色

#### 学生端 AI 推荐组件

编辑 `AiRecommendation.vue`:

```javascript
data() {
  return {
    themeColor: '#409EFF'  // 修改此值
  }
}
```

#### 教师端 AI 工具组件

编辑 `TeacherAiTools.vue`:

```javascript
data() {
  return {
    themeColor: '#67C23A'  // 修改此值
  }
}
```

### 调整悬浮按钮位置

修改 CSS 中的 `bottom` 和 `right` 值：

```scss
.ai-recommendation-container {
  position: fixed;
  right: 20px;     // 调整水平位置
  bottom: 100px;   // 调整垂直位置
  z-index: 999;
}
```

---

## 🐛 常见问题

### Q1: 悬浮按钮不显示

**原因**: 用户未登录或权限不足

**解决**:
- 确认已登录
- 检查 localStorage 中的 Token
- 学生端需要 `Token && notAdmin` 条件为真
- 教师端需要 `sessionTable === 'jiaoshi'`

### Q2: 图表不显示

**原因**: ECharts 未正确加载或数据为空

**解决**:
1. 检查浏览器控制台是否有错误
2. 确认后端 API 返回数据格式正确
3. 刷新页面重新初始化图表

### Q3: AI 推荐无结果

**原因**: API 调用失败或数据不足

**解决**:
1. 检查 AI API Key 是否配置正确
2. 确认网络连接正常
3. 查看浏览器控制台的错误信息
4. 检查后端日志

### Q4: 组件样式冲突

**原因**: 与现有样式冲突

**解决**:
- 使用 scoped 样式
- 增加 CSS 特异性
- 使用 !important（谨慎使用）

---

## 📊 数据流说明

### 学生端推荐流程

```
用户点击推荐 
  ↓
发送请求到 /aiagent/recommend/get
  ↓
后端分析用户历史数据
  ↓
调用 AI API 生成推荐
  ↓
返回推荐结果
  ↓
前端展示推荐列表
```

### 管理员数据分析流程

```
页面加载
  ↓
调用 /analytics/dashboard
  ↓
后端聚合统计数据
  ↓
返回概览数据和详细统计
  ↓
前端渲染统计卡片和图表
  ↓
AI 分析数据生成建议
```

### 教师端审核流程

```
教师选择待审核课题
  ↓
点击"AI审核"按钮
  ↓
发送课题信息到 AI
  ↓
AI 分析课题质量
  ↓
返回审核意见
  ↓
展示给教师参考
```

---

## 🚀 性能优化建议

### 1. 图表懒加载

只在面板打开时初始化图表：

```javascript
togglePanel() {
  this.showPanel = !this.showPanel
  if (this.showPanel && !this.chartsInitialized) {
    this.initCharts()
    this.chartsInitialized = true
  }
}
```

### 2. 数据缓存

缓存推荐结果，避免重复请求：

```javascript
// 检查缓存
const cached = localStorage.getItem('recommendations')
if (cached && !isExpired(cached)) {
  this.recommendations = JSON.parse(cached)
  return
}
```

### 3. 防抖处理

防止频繁点击导致多次请求：

```javascript
import { debounce } from 'lodash'

methods: {
  getRecommendations: debounce(function() {
    // 实际逻辑
  }, 500)
}
```

---

## 📝 扩展开发

### 添加新的推荐类型

1. 在 `AiRecommendation.vue` 中添加新的 radio 选项
2. 在后端实现相应的推荐逻辑
3. 更新 API 接口

### 添加新的图表类型

1. 在 `AdminDashboard.vue` 中添加新的 chart-card
2. 创建对应的渲染方法
3. 在 ECharts 官网选择合适的图表类型

### 添加教师新功能

1. 在 `TeacherAiTools.vue` 中添加新的 tab-pane
2. 实现相应的业务逻辑
3. 调用后端 AI 接口

---

## 🔗 相关文档

- [AI_API_GUIDE.md](../AI_API_GUIDE.md) - AI API 配置详解
- [AI_QUICK_START.md](../AI_QUICK_START.md) - AI 功能快速开始
- [DATABASE_SETUP.md](../DATABASE_SETUP.md) - 数据库设置指南
- [TROUBLESHOOTING.md](../TROUBLESHOOTING.md) - 故障排查

---

## ✅ 验收清单

- [ ] 学生端可以看到 AI 推荐悬浮按钮
- [ ] 点击按钮可以打开推荐面板
- [ ] 能够获取课题推荐
- [ ] 管理员可以看到数据分析面板
- [ ] 所有图表正常显示
- [ ] 教师可以看到 AI 助手按钮
- [ ] 教师可以使用课题审核功能
- [ ] 教师可以查看学生进度
- [ ] 所有功能在不同浏览器下正常工作
- [ ] 移动端响应式布局正常

---

## 📞 技术支持

如遇到问题，请：

1. 查看浏览器控制台错误信息
2. 检查后端日志
3. 查阅本文档的常见问题部分
4. 联系开发团队

---

**集成完成！** 🎉

现在您的系统已经具备了完整的 AI 智能功能，包括学生推荐、管理分析和教师辅助三大模块。
