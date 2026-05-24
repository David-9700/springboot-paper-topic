# 项目文档截图指南

本文档说明需要在 `docs/screenshots/` 目录下放置的截图文件。

## 📸 必需截图清单

### CI/CD Pipeline 相关截图

#### 1. Build 阶段
**文件**: `docs/screenshots/ci-build-success.png`  
**内容**: GitHub Actions Build 阶段成功执行的截图  
**获取方式**: 
1. 访问 GitHub Actions 页面
2. 点击最近的 workflow run
3. 截取 "Build with Maven" 步骤成功的画面
4. 应显示: ✅ Build with Maven, JAR 包生成信息

---

#### 2. Test 阶段
**文件**: `docs/screenshots/ci-test-results.png`  
**内容**: 单元测试执行结果  
**获取方式**:
1. GitHub Actions → Test job
2. 截取 "Run Unit Tests" 步骤输出
3. 应显示: Tests passed, 测试数量, 执行时间

---

#### 3. 代码覆盖率
**文件**: `docs/screenshots/jacoco-coverage.png`  
**内容**: JaCoCo 覆盖率报告  
**获取方式**:
1. 本地运行: `mvn jacoco:report`
2. 打开: `target/site/jacoco/index.html`
3. 截取覆盖率概览页面
4. 或使用 Codecov 网页截图

---

#### 4. Docker 构建
**文件**: `docs/screenshots/docker-build.png`  
**内容**: Docker 镜像构建和推送成功  
**获取方式**:
1. GitHub Actions → Deploy job
2. 截取 "Build and push Docker image" 步骤
3. 应显示: Image built, Pushed to Docker Hub

---

#### 5. 生产部署
**文件**: `docs/screenshots/production-deploy.png`  
**内容**: 生产环境部署完成  
**获取方式**:
1. 截取 "Deploy to production" 步骤成功
2. 或截取服务器 docker ps 输出
3. 显示容器正在运行

---

### Agentic AI 功能截图

#### 6. AI 聊天界面（学生端）
**文件**: `docs/screenshots/ai-chat-ui.png`  
**内容**: 学生端 AI 推荐悬浮按钮和面板  
**获取方式**:
1. 启动应用，以学生身份登录
2. 访问前台首页
3. 右下角应看到紫色 "AI推荐" 按钮
4. 点击按钮，截取展开的面板
5. 显示三种推荐类型选项

---

#### 7. AI 对话响应
**文件**: `docs/screenshots/ai-chat-response.png`  
**内容**: AI 返回的对话内容  
**获取方式**:
1. 在 AI 推荐面板中输入问题
2. 例如: "请推荐计算机专业的课题"
3. 等待 AI 响应
4. 截取完整的对话内容
5. 应显示 AI 回复的文本

---

#### 8. AI 推荐面板
**文件**: `docs/screenshots/ai-recommend-panel.png`  
**内容**: 推荐结果列表  
**获取方式**:
1. 点击"获取推荐"按钮
2. 等待推荐结果加载
3. 截取推荐卡片列表
4. 每个卡片应显示: 标题、描述、标签、匹配度

---

#### 9. 推荐详情
**文件**: `docs/screenshots/recommendation-detail.png`  
**内容**: 单个推荐项的详细信息  
**获取方式**:
1. 点击某个推荐卡片
2. 截取弹出的详情窗口
3. 或直接截取带有匹配度评分的卡片

---

#### 10. 管理员数据面板
**文件**: `docs/screenshots/admin-dashboard.png`  
**内容**: 完整的数据分析面板  
**获取方式**:
1. 以管理员身份登录后台
2. 访问首页
3. 截取整个页面
4. 应显示: 统计卡片 + 图表区域 + AI 建议

---

#### 11. 数据可视化图表
**文件**: `docs/screenshots/dashboard-charts.png`  
**内容**: ECharts 图表特写  
**获取方式**:
1. 在管理员面板中
2. 分别截取各个图表:
   - 课题分类饼图
   - 学院分布柱状图
   - 选题趋势折线图
3. 可以拼接成一张图或分别保存

---

#### 12. AI 智能建议
**文件**: `docs/screenshots/ai-suggestions.png`  
**内容**: AI 生成的数据分析建议  
**获取方式**:
1. 在管理员面板底部
2. 找到 "AI 智能分析建议" 区域
3. 截取建议列表
4. 应显示带灯泡图标的建议条目

---

#### 13. 教师 AI 工具箱
**文件**: `docs/screenshots/teacher-ai-tools.png`  
**内容**: 教师端 AI 助手主界面  
**获取方式**:
1. 以教师身份登录后台
2. 右下角绿色 "AI助手" 按钮
3. 点击展开面板
4. 截取三个 Tab: 课题审核、学生指导、成绩评估

---

#### 14. 课题审核
**文件**: `docs/screenshots/topic-review.png`  
**内容**: AI 课题审核功能  
**获取方式**:
1. 在教师 AI 工具箱中选择"课题审核"
2. 点击某个课题的"AI审核"按钮
3. 截取弹出的审核意见对话框
4. 应显示 AI 生成的详细审核意见

---

#### 15. 学生指导
**文件**: `docs/screenshots/student-guidance.png`  
**内容**: AI 学生指导建议  
**获取方式**:
1. 在教师 AI 工具箱中选择"学生指导"
2. 选择一个学生
3. 点击"获取 AI 指导建议"
4. 截取生成的建议内容

---

## 🎨 截图要求

### 通用规范

1. **分辨率**: 1920x1080 或更高
2. **格式**: PNG（支持透明背景）
3. **文件大小**: 每张图片 < 500KB
4. **清晰度**: 文字清晰可读
5. **完整性**: 包含必要的上下文信息

### 内容要求

1. **敏感信息脱敏**:
   - API Key 打码
   - 真实姓名打码
   - 电话号码打码
   - 邮箱地址打码

2. **标注说明**:
   - 使用红色箭头标注重点
   - 添加文字说明框
   - 高亮关键数据

3. **浏览器要求**:
   - 使用 Chrome 或 Firefox
   - 清除缓存后截图
   - 缩放比例 100%

---

## 📷 截图工具推荐

### Windows

1. **Snip & Sketch** (系统自带)
   - Win + Shift + S 快捷键
   - 支持矩形、自由形式截图

2. **ShareX** (免费开源)
   - 功能强大，支持滚动截图
   - 自动上传和标注

3. **PicPick** (轻量级)
   - 简单易用
   - 支持编辑和标注

### Mac

1. **Screenshot** (系统自带)
   - Cmd + Shift + 4
   - 支持区域截图

2. **CleanShot X** (付费)
   - 专业截图工具
   - 支持录制 GIF

### Linux

1. **Flameshot**
   - 功能丰富
   - 支持即时标注

2. **Shutter**
   - 老牌截图工具
   - 支持编辑

---

## 🖼️ 截图示例模板

### CI/CD 截图模板

```
┌─────────────────────────────────────┐
│  GitHub Actions - Workflow Run      │
├─────────────────────────────────────┤
│                                     │
│  ✓ Set up JDK 8              2s    │
│  ✓ Build with Maven          45s   │
│    └─ target/app.jar generated     │
│  ✓ Upload artifacts          3s    │
│                                     │
│  Status: ✅ Success                 │
│  Duration: 50s                      │
└─────────────────────────────────────┘
         ↑ 标注: 构建成功
```

### AI 功能截图模板

```
┌─────────────────────────────────────┐
│  AI 智能推荐                        │
├─────────────────────────────────────┤
│  [课题推荐] [导师推荐] [学习资源]   │
│                                     │
│  ┌─────────────────────────────┐   │
│  │ 基于深度学习的图像识别系统   │   │
│  │ 匹配度: 92% ⭐⭐⭐⭐⭐      │   │
│  │ 标签: AI, 图像处理, Python  │   │
│  └─────────────────────────────┘   │
│                                     │
│  [获取推荐]                         │
└─────────────────────────────────────┘
         ↑ 标注: 推荐结果
```

---

## ✅ 截图检查清单

在提交截图前，请确认：

- [ ] 所有 15 张截图都已准备
- [ ] 图片命名与文档要求一致
- [ ] 图片存放在 `docs/screenshots/` 目录
- [ ] 敏感信息已脱敏
- [ ] 图片清晰可读
- [ ] 关键内容已标注
- [ ] 文件大小符合要求
- [ ] 格式为 PNG

---

## 📁 目录结构

```
docs/
└── screenshots/
    ├── ci-build-success.png
    ├── ci-test-results.png
    ├── jacoco-coverage.png
    ├── docker-build.png
    ├── production-deploy.png
    ├── ai-chat-ui.png
    ├── ai-chat-response.png
    ├── ai-recommend-panel.png
    ├── recommendation-detail.png
    ├── admin-dashboard.png
    ├── dashboard-charts.png
    ├── ai-suggestions.png
    ├── teacher-ai-tools.png
    ├── topic-review.png
    └── student-guidance.png
```

---

## 💡 提示

1. **批量截图**: 可以先运行系统，一次性截取所有 UI 页面
2. **占位符**: 如果暂时没有截图，可以使用在线占位图服务
   - https://via.placeholder.com/800x600?text=Screenshot+Placeholder
3. **后续补充**: 可以先提交文档，截图稍后补充
4. **版本管理**: 截图也建议纳入 Git 版本控制

---

**截图准备完成后，文档即可正式发布！** 🎉
