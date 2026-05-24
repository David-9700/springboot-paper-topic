<template>
  <div class="teacher-ai-tools">
    <!-- 悬浮按钮 -->
    <div 
      class="ai-float-button"
      @click="togglePanel"
      :style="{ backgroundColor: themeColor }"
    >
      <i class="el-icon-s-tools"></i>
      <span class="button-text">AI助手</span>
    </div>

    <!-- AI工具面板 -->
    <transition name="slide-fade">
      <div v-if="showPanel" class="ai-panel">
        <div class="panel-header" :style="{ backgroundColor: themeColor }">
          <h3><i class="el-icon-s-tools"></i> 教师 AI 工具箱</h3>
          <i class="el-icon-close close-btn" @click="togglePanel"></i>
        </div>

        <div class="panel-content">
          <!-- 功能选项卡 -->
          <el-tabs v-model="activeTab" type="border-card">
            <!-- 课题审核辅助 -->
            <el-tab-pane label="课题审核" name="review">
              <div class="tool-section">
                <h4>待审核课题</h4>
                <div v-if="pendingTopics.length > 0" class="topic-list">
                  <div 
                    v-for="topic in pendingTopics" 
                    :key="topic.id"
                    class="topic-item"
                  >
                    <div class="topic-info">
                      <div class="topic-title">{{ topic.title }}</div>
                      <div class="topic-student">{{ topic.studentName }}</div>
                    </div>
                    <div class="topic-actions">
                      <el-button 
                        size="mini" 
                        type="success"
                        @click="aiReviewTopic(topic)"
                        :loading="topic.reviewing"
                      >
                        AI审核
                      </el-button>
                    </div>
                  </div>
                </div>
                <div v-else class="empty-tip">
                  <i class="el-icon-document"></i>
                  <p>暂无待审核课题</p>
                </div>
              </div>
            </el-tab-pane>

            <!-- 学生指导 -->
            <el-tab-pane label="学生指导" name="guidance">
              <div class="tool-section">
                <h4>指导学生列表</h4>
                <el-select 
                  v-model="selectedStudent" 
                  placeholder="选择学生"
                  style="width: 100%; margin-bottom: 12px"
                  @change="loadStudentProgress"
                >
                  <el-option
                    v-for="student in myStudents"
                    :key="student.id"
                    :label="student.name"
                    :value="student.id"
                  />
                </el-select>

                <div v-if="selectedStudent && studentProgress" class="progress-info">
                  <div class="progress-item">
                    <span class="label">当前阶段:</span>
                    <span class="value">{{ studentProgress.stage }}</span>
                  </div>
                  <div class="progress-item">
                    <span class="label">完成进度:</span>
                    <el-progress 
                      :percentage="studentProgress.percentage" 
                      :status="getProgressStatus(studentProgress.percentage)"
                    />
                  </div>
                  
                  <el-button 
                    type="primary" 
                    icon="el-icon-magic-stick"
                    @click="getAiGuidance"
                    :loading="guidanceLoading"
                    block
                    style="margin-top: 12px"
                  >
                    获取 AI 指导建议
                  </el-button>

                  <div v-if="aiGuidance" class="guidance-result">
                    <h5>AI 指导建议:</h5>
                    <p>{{ aiGuidance }}</p>
                  </div>
                </div>
              </div>
            </el-tab-pane>

            <!-- 成绩评估 -->
            <el-tab-pane label="成绩评估" name="assessment">
              <div class="tool-section">
                <h4>批量成绩评估</h4>
                <el-alert
                  title="AI 可根据学生表现自动生成初步评分建议"
                  type="info"
                  :closable="false"
                  show-icon
                  style="margin-bottom: 12px"
                />
                
                <el-button 
                  type="warning" 
                  icon="el-icon-data-analysis"
                  @click="batchAssessment"
                  :loading="assessmentLoading"
                  block
                >
                  开始 AI 评估
                </el-button>

                <div v-if="assessmentResults.length > 0" class="assessment-list">
                  <div 
                    v-for="result in assessmentResults" 
                    :key="result.studentId"
                    class="assessment-item"
                  >
                    <div class="student-name">{{ result.studentName }}</div>
                    <div class="score-info">
                      <el-tag size="small" :type="getScoreType(result.suggestedScore)">
                        {{ result.suggestedScore }}分
                      </el-tag>
                      <span class="reason">{{ result.reason }}</span>
                    </div>
                  </div>
                </div>
              </div>
            </el-tab-pane>
          </el-tabs>
        </div>
      </div>
    </transition>
  </div>
</template>

<script>
export default {
  name: 'TeacherAiTools',
  data() {
    return {
      showPanel: false,
      activeTab: 'review',
      themeColor: '#67C23A',
      
      // 课题审核
      pendingTopics: [],
      
      // 学生指导
      myStudents: [],
      selectedStudent: null,
      studentProgress: null,
      guidanceLoading: false,
      aiGuidance: null,
      
      // 成绩评估
      assessmentLoading: false,
      assessmentResults: []
    }
  },
  mounted() {
    this.loadPendingTopics()
    this.loadMyStudents()
  },
  methods: {
    togglePanel() {
      this.showPanel = !this.showPanel
    },

    // 加载待审核课题
    async loadPendingTopics() {
      try {
        const response = await this.$http.get('/kaitibaogao/list', {
          params: {
            page: 1,
            limit: 10,
            shenhe: '待审核'
          }
        })
        
        if (response.data.code === 0) {
          this.pendingTopics = response.data.data.list.map(item => ({
            id: item.id,
            title: item.kaititimu,
            studentName: item.xueshengxingming,
            reviewing: false
          }))
        }
      } catch (error) {
        console.error('加载待审核课题失败:', error)
      }
    },

    // AI 审核课题
    async aiReviewTopic(topic) {
      topic.reviewing = true
      
      try {
        const response = await this.$http.post('/aiagent/chat/send', {
          userId: this.$store.state.user?.id || localStorage.getItem('userid'),
          userType: 'jiaoshi',
          message: `请评估以下开题报告的质量：${topic.title}，给出审核意见和改进建议`
        })
        
        if (response.data.code === 0) {
          this.$alert(response.data.data.aiMessage.content, 'AI 审核意见', {
            confirmButtonText: '确定',
            type: 'info'
          })
        }
      } catch (error) {
        this.$message.error('AI 审核失败')
      } finally {
        topic.reviewing = false
      }
    },

    // 加载我的学生
    async loadMyStudents() {
      try {
        const teacherId = localStorage.getItem('userid')
        const response = await this.$http.get('/xuantixinxi/list', {
          params: {
            page: 1,
            limit: 100,
            jiaoshiid: teacherId
          }
        })
        
        if (response.data.code === 0) {
          const students = new Map()
          response.data.data.list.forEach(item => {
            if (!students.has(item.xueshengid)) {
              students.set(item.xueshengid, {
                id: item.xueshengid,
                name: item.xueshengxingming
              })
            }
          })
          this.myStudents = Array.from(students.values())
        }
      } catch (error) {
        console.error('加载学生列表失败:', error)
      }
    },

    // 加载学生进度
    async loadStudentProgress(studentId) {
      try {
        const response = await this.$http.get('/xuantixinxi/list', {
          params: {
            page: 1,
            limit: 1,
            xueshengid: studentId
          }
        })
        
        if (response.data.code === 0 && response.data.data.list.length > 0) {
          const selection = response.data.data.list[0]
          
          // 计算进度
          let percentage = 0
          let stage = '选题阶段'
          
          if (selection.kaitibaogao) {
            percentage += 30
            stage = '开题阶段'
          }
          if (selection.renwushu) {
            percentage += 30
            stage = '任务书阶段'
          }
          if (selection.lunwen) {
            percentage += 40
            stage = '论文写作阶段'
          }
          
          this.studentProgress = {
            stage,
            percentage
          }
        }
      } catch (error) {
        console.error('加载学生进度失败:', error)
      }
    },

    // 获取 AI 指导建议
    async getAiGuidance() {
      this.guidanceLoading = true
      
      try {
        const response = await this.$http.post('/aiagent/chat/send', {
          userId: localStorage.getItem('userid'),
          userType: 'jiaoshi',
          message: `学生在${this.studentProgress.stage}，完成进度${this.studentProgress.percentage}%，请给出指导建议`
        })
        
        if (response.data.code === 0) {
          this.aiGuidance = response.data.data.aiMessage.content
        }
      } catch (error) {
        this.$message.error('获取建议失败')
      } finally {
        this.guidanceLoading = false
      }
    },

    getProgressStatus(percentage) {
      if (percentage >= 80) return 'success'
      if (percentage >= 50) return 'warning'
      return 'exception'
    },

    // 批量成绩评估
    async batchAssessment() {
      this.assessmentLoading = true
      
      try {
        // 这里调用后端 AI 评估接口
        const response = await this.$http.post('/aiagent/assessment/batch', {
          teacherId: localStorage.getItem('userid')
        })
        
        if (response.data.code === 0) {
          this.assessmentResults = response.data.data
          this.$message.success('评估完成')
        }
      } catch (error) {
        this.$message.error('评估失败')
      } finally {
        this.assessmentLoading = false
      }
    },

    getScoreType(score) {
      if (score >= 90) return 'success'
      if (score >= 75) return ''
      if (score >= 60) return 'warning'
      return 'danger'
    }
  }
}
</script>

<style scoped lang="scss">
.teacher-ai-tools {
  position: fixed;
  right: 20px;
  bottom: 100px;
  z-index: 999;
}

.ai-float-button {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  color: #fff;
  cursor: pointer;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  transition: all 0.3s;

  &:hover {
    transform: scale(1.1);
    box-shadow: 0 6px 16px rgba(0, 0, 0, 0.2);
  }

  i {
    font-size: 24px;
    margin-bottom: 2px;
  }

  .button-text {
    font-size: 10px;
  }
}

.ai-panel {
  position: fixed;
  right: 20px;
  bottom: 180px;
  width: 450px;
  max-height: 650px;
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.15);
  overflow: hidden;
  display: flex;
  flex-direction: column;

  .panel-header {
    padding: 16px 20px;
    color: #fff;
    display: flex;
    justify-content: space-between;
    align-items: center;

    h3 {
      margin: 0;
      font-size: 16px;
      font-weight: 600;

      i {
        margin-right: 8px;
      }
    }

    .close-btn {
      font-size: 20px;
      cursor: pointer;
      transition: transform 0.3s;

      &:hover {
        transform: rotate(90deg);
      }
    }
  }

  .panel-content {
    flex: 1;
    overflow-y: auto;
    padding: 16px;

    .tool-section {
      h4 {
        margin: 0 0 12px 0;
        font-size: 14px;
        color: #303133;
      }

      .topic-list {
        .topic-item {
          padding: 12px;
          margin-bottom: 8px;
          border: 1px solid #e4e7ed;
          border-radius: 6px;
          display: flex;
          justify-content: space-between;
          align-items: center;

          .topic-info {
            flex: 1;

            .topic-title {
              font-size: 14px;
              font-weight: 500;
              color: #303133;
              margin-bottom: 4px;
            }

            .topic-student {
              font-size: 12px;
              color: #909399;
            }
          }
        }
      }

      .empty-tip {
        text-align: center;
        padding: 30px 0;
        color: #909399;

        i {
          font-size: 48px;
          display: block;
          margin-bottom: 8px;
        }
      }

      .progress-info {
        .progress-item {
          margin-bottom: 12px;

          .label {
            display: inline-block;
            width: 80px;
            font-size: 14px;
            color: #606266;
          }

          .value {
            font-size: 14px;
            color: #303133;
            font-weight: 500;
          }
        }

        .guidance-result {
          margin-top: 16px;
          padding: 12px;
          background: #f4f4f5;
          border-radius: 6px;

          h5 {
            margin: 0 0 8px 0;
            font-size: 14px;
            color: #303133;
          }

          p {
            margin: 0;
            font-size: 13px;
            color: #606266;
            line-height: 1.6;
          }
        }
      }

      .assessment-list {
        margin-top: 12px;

        .assessment-item {
          padding: 10px;
          margin-bottom: 8px;
          border: 1px solid #e4e7ed;
          border-radius: 6px;

          .student-name {
            font-size: 14px;
            font-weight: 500;
            color: #303133;
            margin-bottom: 6px;
          }

          .score-info {
            display: flex;
            align-items: center;
            gap: 8px;

            .reason {
              font-size: 12px;
              color: #909399;
            }
          }
        }
      }
    }
  }
}

// 动画效果
.slide-fade-enter-active {
  transition: all 0.3s ease;
}

.slide-fade-leave-active {
  transition: all 0.3s ease;
}

.slide-fade-enter {
  transform: translateX(100%);
  opacity: 0;
}

.slide-fade-leave-to {
  transform: translateX(100%);
  opacity: 0;
}

// 响应式
@media (max-width: 768px) {
  .ai-panel {
    width: calc(100vw - 40px);
    right: 20px;
    bottom: 100px;
  }
}
</style>
