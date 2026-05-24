<template>
  <div class="ai-recommendation-container">
    <!-- 悬浮按钮 -->
    <div 
      v-if="showFloatButton" 
      class="ai-float-button"
      @click="togglePanel"
      :style="{ backgroundColor: themeColor }"
    >
      <i class="el-icon-magic-stick"></i>
      <span class="button-text">AI推荐</span>
      <el-badge :value="unreadCount" :hidden="unreadCount === 0" class="badge"></el-badge>
    </div>

    <!-- AI推荐面板 -->
    <transition name="slide-fade">
      <div v-if="showPanel" class="ai-panel">
        <div class="panel-header" :style="{ backgroundColor: themeColor }">
          <h3><i class="el-icon-magic-stick"></i> AI智能推荐</h3>
          <i class="el-icon-close close-btn" @click="togglePanel"></i>
        </div>

        <div class="panel-content">
          <!-- 推荐类型选择 -->
          <div class="recommend-type">
            <el-radio-group v-model="recommendType" size="small" @change="handleTypeChange">
              <el-radio-button label="topic">课题推荐</el-radio-button>
              <el-radio-button label="teacher">导师推荐</el-radio-button>
              <el-radio-button label="resource">学习资源</el-radio-button>
            </el-radio-group>
          </div>

          <!-- 加载状态 -->
          <div v-if="loading" class="loading-container">
            <i class="el-icon-loading"></i>
            <p>AI正在分析中...</p>
          </div>

          <!-- 推荐结果 -->
          <div v-else-if="recommendations.length > 0" class="recommend-list">
            <div 
              v-for="(item, index) in recommendations" 
              :key="index"
              class="recommend-item"
              @click="handleItemClick(item)"
            >
              <div class="item-header">
                <span class="item-title">{{ item.title }}</span>
                <el-tag size="mini" type="success" v-if="item.matchScore">
                  匹配度 {{ item.matchScore }}%
                </el-tag>
              </div>
              <p class="item-desc">{{ item.description }}</p>
              <div class="item-footer">
                <el-tag size="mini" v-for="tag in item.tags" :key="tag">{{ tag }}</el-tag>
              </div>
            </div>
          </div>

          <!-- 空状态 -->
          <div v-else class="empty-state">
            <i class="el-icon-info"></i>
            <p>点击"获取推荐"按钮开始</p>
          </div>

          <!-- 操作按钮 -->
          <div class="panel-actions">
            <el-button 
              type="primary" 
              icon="el-icon-refresh" 
              @click="getRecommendations"
              :loading="loading"
              block
            >
              获取推荐
            </el-button>
          </div>
        </div>
      </div>
    </transition>
  </div>
</template>

<script>
export default {
  name: 'AiRecommendation',
  props: {
    userId: {
      type: [String, Number],
      required: true
    },
    userType: {
      type: String,
      default: 'xuesheng'
    }
  },
  data() {
    return {
      showPanel: false,
      showFloatButton: true,
      loading: false,
      recommendType: 'topic',
      recommendations: [],
      unreadCount: 0,
      themeColor: '#409EFF'
    }
  },
  methods: {
    togglePanel() {
      this.showPanel = !this.showPanel
      if (this.showPanel && this.recommendations.length === 0) {
        this.getRecommendations()
      }
    },

    async getRecommendations() {
      this.loading = true
      try {
        const response = await this.$http.post('/aiagent/recommend/get', {
          userId: this.userId,
          userType: this.userType,
          recommendType: this.recommendType
        })

        if (response.data.code === 0) {
          this.recommendations = response.data.data || []
          this.unreadCount = 0
        } else {
          this.$message.error(response.data.msg || '获取推荐失败')
        }
      } catch (error) {
        console.error('获取推荐失败:', error)
        this.$message.error('网络错误，请稍后重试')
      } finally {
        this.loading = false
      }
    },

    handleTypeChange() {
      this.recommendations = []
      this.getRecommendations()
    },

    handleItemClick(item) {
      // 根据推荐类型跳转到不同页面
      if (this.recommendType === 'topic') {
        this.$router.push({
          path: '/index/ketixinxi',
          query: { id: item.id }
        })
      } else if (this.recommendType === 'teacher') {
        this.$router.push({
          path: '/index/jiaoshi',
          query: { id: item.id }
        })
      }
      
      this.showPanel = false
    }
  },
  mounted() {
    // 从 localStorage 获取用户信息
    const sessionForm = JSON.parse(localStorage.getItem('sessionForm') || '{}')
    if (sessionForm.id) {
      this.userId = sessionForm.id
    }
    const userTable = localStorage.getItem('frontSessionTable') || 'xuesheng'
    this.userType = userTable.replace(/"/g, '')
  }
}
</script>

<style scoped lang="scss">
.ai-recommendation-container {
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
  position: relative;

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

  .badge {
    position: absolute;
    top: -5px;
    right: -5px;
  }
}

.ai-panel {
  position: fixed;
  right: 20px;
  bottom: 180px;
  width: 380px;
  max-height: 600px;
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

    .recommend-type {
      margin-bottom: 16px;
    }

    .loading-container {
      text-align: center;
      padding: 40px 0;
      color: #909399;

      i {
        font-size: 32px;
        display: block;
        margin-bottom: 12px;
      }
    }

    .recommend-list {
      .recommend-item {
        padding: 12px;
        margin-bottom: 12px;
        border: 1px solid #e4e7ed;
        border-radius: 8px;
        cursor: pointer;
        transition: all 0.3s;

        &:hover {
          border-color: #409EFF;
          box-shadow: 0 2px 8px rgba(64, 158, 255, 0.2);
        }

        .item-header {
          display: flex;
          justify-content: space-between;
          align-items: center;
          margin-bottom: 8px;

          .item-title {
            font-size: 14px;
            font-weight: 600;
            color: #303133;
            flex: 1;
          }
        }

        .item-desc {
          font-size: 12px;
          color: #606266;
          margin: 8px 0;
          line-height: 1.5;
        }

        .item-footer {
          display: flex;
          gap: 6px;
          flex-wrap: wrap;
        }
      }
    }

    .empty-state {
      text-align: center;
      padding: 40px 0;
      color: #909399;

      i {
        font-size: 48px;
        display: block;
        margin-bottom: 12px;
      }
    }

    .panel-actions {
      margin-top: 16px;
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
