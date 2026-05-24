<template>
  <div class="dashboard-container">
    <!-- 统计卡片 -->
    <div class="stat-cards">
      <div 
        v-for="(stat, index) in stats" 
        :key="index"
        class="stat-card"
        :style="{ borderColor: stat.color }"
      >
        <div class="stat-icon" :style="{ backgroundColor: stat.color }">
          <i :class="stat.icon"></i>
        </div>
        <div class="stat-info">
          <div class="stat-value">{{ stat.value }}</div>
          <div class="stat-label">{{ stat.label }}</div>
        </div>
      </div>
    </div>

    <!-- 图表区域 -->
    <div class="charts-grid">
      <!-- 课题分类饼图 -->
      <div class="chart-card">
        <h3 class="chart-title">课题分类统计</h3>
        <div ref="categoryChart" class="chart-container"></div>
      </div>

      <!-- 学院分布柱状图 -->
      <div class="chart-card">
        <h3 class="chart-title">学院分布</h3>
        <div ref="collegeChart" class="chart-container"></div>
      </div>

      <!-- 选题趋势折线图 -->
      <div class="chart-card full-width">
        <h3 class="chart-title">选题趋势</h3>
        <div ref="trendChart" class="chart-container"></div>
      </div>

      <!-- 教师指导排行 -->
      <div class="chart-card">
        <h3 class="chart-title">教师指导学生排行</h3>
        <div ref="teacherChart" class="chart-container"></div>
      </div>

      <!-- 开题报告统计 -->
      <div class="chart-card">
        <h3 class="chart-title">开题报告状态</h3>
        <div ref="reportChart" class="chart-container"></div>
      </div>
    </div>

    <!-- AI 智能分析建议 -->
    <div class="ai-suggestions" v-if="aiSuggestions.length > 0">
      <h3><i class="el-icon-magic-stick"></i> AI 智能分析建议</h3>
      <div class="suggestion-list">
        <div 
          v-for="(suggestion, index) in aiSuggestions" 
          :key="index"
          class="suggestion-item"
        >
          <i class="el-icon-lightbulb"></i>
          <span>{{ suggestion }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import * as echarts from 'echarts'

export default {
  name: 'AdminDashboard',
  data() {
    return {
      stats: [
        { label: '学生总数', value: 0, icon: 'el-icon-user', color: '#409EFF' },
        { label: '教师总数', value: 0, icon: 'el-icon-s-custom', color: '#67C23A' },
        { label: '课题总数', value: 0, icon: 'el-icon-document', color: '#E6A23C' },
        { label: '已选题数', value: 0, icon: 'el-icon-check', color: '#F56C6C' }
      ],
      charts: {},
      aiSuggestions: []
    }
  },
  mounted() {
    this.loadDashboardData()
  },
  beforeDestroy() {
    // 销毁图表实例
    Object.values(this.charts).forEach(chart => {
      if (chart) chart.dispose()
    })
  },
  methods: {
    async loadDashboardData() {
      try {
        const response = await this.$http.get('/analytics/dashboard')
        
        if (response.data.code === 0) {
          const data = response.data.data
          
          // 更新统计卡片
          this.updateStatCards(data.overview)
          
          // 渲染图表
          this.renderCategoryChart(data.topicCategories)
          this.renderCollegeChart(data.collegeDistribution)
          this.renderTrendChart(data.selectionTrend)
          this.renderTeacherChart(data.teacherRanking)
          this.renderReportChart(data.reportStatus)
          
          // 生成 AI 建议
          this.generateAiSuggestions(data)
        }
      } catch (error) {
        console.error('加载数据失败:', error)
        this.$message.error('加载数据失败')
      }
    },

    updateStatCards(overview) {
      if (!overview) return
      
      this.stats[0].value = overview.studentCount || 0
      this.stats[1].value = overview.teacherCount || 0
      this.stats[2].value = overview.topicCount || 0
      this.stats[3].value = overview.selectedCount || 0
    },

    renderCategoryChart(categories) {
      if (!categories || categories.length === 0) return

      const chartDom = this.$refs.categoryChart
      const myChart = echarts.init(chartDom)
      this.charts.category = myChart

      const option = {
        tooltip: {
          trigger: 'item',
          formatter: '{b}: {c} ({d}%)'
        },
        legend: {
          orient: 'vertical',
          right: 10,
          top: 'center'
        },
        series: [
          {
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: 16,
                fontWeight: 'bold'
              }
            },
            data: categories.map(item => ({
              name: item.name,
              value: item.value
            }))
          }
        ]
      }

      myChart.setOption(option)
    },

    renderCollegeChart(colleges) {
      if (!colleges || colleges.length === 0) return

      const chartDom = this.$refs.collegeChart
      const myChart = echarts.init(chartDom)
      this.charts.college = myChart

      const option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          data: colleges.map(item => item.name),
          axisLabel: {
            rotate: 45,
            interval: 0
          }
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: '学生数',
            type: 'bar',
            data: colleges.map(item => item.value),
            itemStyle: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                { offset: 0, color: '#83bff6' },
                { offset: 0.5, color: '#188df0' },
                { offset: 1, color: '#188df0' }
              ])
            }
          }
        ]
      }

      myChart.setOption(option)
    },

    renderTrendChart(trend) {
      if (!trend || trend.length === 0) return

      const chartDom = this.$refs.trendChart
      const myChart = echarts.init(chartDom)
      this.charts.trend = myChart

      const option = {
        tooltip: {
          trigger: 'axis'
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: trend.map(item => item.date)
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: '选题数',
            type: 'line',
            smooth: true,
            data: trend.map(item => item.count),
            areaStyle: {
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                { offset: 0, color: 'rgba(64, 158, 255, 0.5)' },
                { offset: 1, color: 'rgba(64, 158, 255, 0.1)' }
              ])
            },
            itemStyle: {
              color: '#409EFF'
            }
          }
        ]
      }

      myChart.setOption(option)
    },

    renderTeacherChart(teachers) {
      if (!teachers || teachers.length === 0) return

      const chartDom = this.$refs.teacherChart
      const myChart = echarts.init(chartDom)
      this.charts.teacher = myChart

      const option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'value'
        },
        yAxis: {
          type: 'category',
          data: teachers.map(item => item.name).reverse(),
          axisLabel: {
            interval: 0
          }
        },
        series: [
          {
            name: '指导学生数',
            type: 'bar',
            data: teachers.map(item => item.value).reverse(),
            itemStyle: {
              color: '#67C23A'
            }
          }
        ]
      }

      myChart.setOption(option)
    },

    renderReportChart(reports) {
      if (!reports || reports.length === 0) return

      const chartDom = this.$refs.reportChart
      const myChart = echarts.init(chartDom)
      this.charts.report = myChart

      const option = {
        tooltip: {
          trigger: 'item',
          formatter: '{b}: {c} ({d}%)'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            type: 'pie',
            radius: '60%',
            data: reports.map(item => ({
              name: item.name,
              value: item.value
            })),
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      }

      myChart.setOption(option)
    },

    generateAiSuggestions(data) {
      const suggestions = []
      
      // 根据数据生成 AI 建议
      if (data.overview) {
        const selectionRate = data.overview.selectedCount / data.overview.topicCount * 100
        
        if (selectionRate < 50) {
          suggestions.push('当前选题率较低，建议增加课题数量或优化课题质量')
        }
        
        if (data.overview.studentCount > data.overview.teacherCount * 20) {
          suggestions.push('师生比例偏高，建议增加指导教师或限制每位教师的指导学生数')
        }
      }

      if (data.collegeDistribution && data.collegeDistribution.length > 0) {
        const maxCollege = data.collegeDistribution.reduce((max, item) => 
          item.value > max.value ? item : max
        )
        suggestions.push(`${maxCollege.name} 学生人数最多，可考虑增加该学院的课题资源`)
      }

      this.aiSuggestions = suggestions
    }
  }
}
</script>

<style scoped lang="scss">
.dashboard-container {
  padding: 20px;
  background: #f5f7fa;
  min-height: calc(100vh - 60px);
}

.stat-cards {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(240px, 1fr));
  gap: 20px;
  margin-bottom: 20px;

  .stat-card {
    background: #fff;
    border-radius: 8px;
    padding: 20px;
    display: flex;
    align-items: center;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    border-left: 4px solid;
    transition: all 0.3s;

    &:hover {
      transform: translateY(-2px);
      box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
    }

    .stat-icon {
      width: 60px;
      height: 60px;
      border-radius: 50%;
      display: flex;
      align-items: center;
      justify-content: center;
      margin-right: 16px;

      i {
        font-size: 28px;
        color: #fff;
      }
    }

    .stat-info {
      flex: 1;

      .stat-value {
        font-size: 28px;
        font-weight: 600;
        color: #303133;
        margin-bottom: 4px;
      }

      .stat-label {
        font-size: 14px;
        color: #909399;
      }
    }
  }
}

.charts-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(400px, 1fr));
  gap: 20px;
  margin-bottom: 20px;

  .chart-card {
    background: #fff;
    border-radius: 8px;
    padding: 20px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);

    &.full-width {
      grid-column: 1 / -1;
    }

    .chart-title {
      margin: 0 0 16px 0;
      font-size: 16px;
      font-weight: 600;
      color: #303133;
    }

    .chart-container {
      height: 300px;
    }
  }
}

.ai-suggestions {
  background: #fff;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);

  h3 {
    margin: 0 0 16px 0;
    font-size: 16px;
    font-weight: 600;
    color: #303133;

    i {
      margin-right: 8px;
      color: #E6A23C;
    }
  }

  .suggestion-list {
    .suggestion-item {
      padding: 12px;
      margin-bottom: 8px;
      background: #fdf6ec;
      border-left: 3px solid #E6A23C;
      border-radius: 4px;
      display: flex;
      align-items: center;

      i {
        margin-right: 8px;
        color: #E6A23C;
        font-size: 16px;
      }

      span {
        color: #606266;
        font-size: 14px;
        line-height: 1.5;
      }
    }
  }
}

// 响应式
@media (max-width: 768px) {
  .charts-grid {
    grid-template-columns: 1fr;
  }

  .stat-cards {
    grid-template-columns: 1fr;
  }
}
</style>
