package com.controller;

import com.annotation.IgnoreAuth;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据分析控制器
 */
@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取系统整体统计数据
     */
    @GetMapping("/system/overview")
    public R getSystemOverview() {
        Map<String, Object> overview = new HashMap<>();

        // 学生总数
        Integer studentCount = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM xuesheng", Integer.class);
        overview.put("studentCount", studentCount != null ? studentCount : 0);

        // 教师总数
        Integer teacherCount = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM jiaoshi", Integer.class);
        overview.put("teacherCount", teacherCount != null ? teacherCount : 0);

        // 课题总数
        Integer topicCount = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM ketixinxi", Integer.class);
        overview.put("topicCount", topicCount != null ? topicCount : 0);

        // 已选题数量
        Integer selectedCount = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM xuantixinxi WHERE zhuangtai = '已选题'", Integer.class);
        overview.put("selectedCount", selectedCount != null ? selectedCount : 0);

        // 待选题数量
        Integer pendingCount = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM xuantixinxi WHERE zhuangtai = '待审核' OR zhuangtai IS NULL", Integer.class);
        overview.put("pendingCount", pendingCount != null ? pendingCount : 0);

        return R.ok().put("data", overview);
    }

    /**
     * 获取课题分类统计
     */
    @GetMapping("/topics/by-category")
    public R getTopicsByCategory() {
        List<Map<String, Object>> result = jdbcTemplate.queryForList(
            "SELECT k.ketifenlei, COUNT(*) as count " +
            "FROM ketixinxi k " +
            "LEFT JOIN ketifenlei kf ON k.ketifenlei = kf.ketifenlei " +
            "GROUP BY k.ketifenlei " +
            "ORDER BY count DESC"
        );
        return R.ok().put("data", result);
    }

    /**
     * 获取各学院学生分布
     */
    @GetMapping("/students/by-college")
    public R getStudentsByCollege() {
        List<Map<String, Object>> result = jdbcTemplate.queryForList(
            "SELECT y.yuanximingcheng, COUNT(*) as count " +
            "FROM xuesheng x " +
            "LEFT JOIN yuanxi y ON x.yuanxi = y.yuanximingcheng " +
            "GROUP BY y.yuanximingcheng " +
            "ORDER BY count DESC"
        );
        return R.ok().put("data", result);
    }

    /**
     * 获取选题趋势（按月统计）
     */
    @GetMapping("/topics/trend")
    public R getTopicSelectionTrend() {
        List<Map<String, Object>> result = jdbcTemplate.queryForList(
            "SELECT DATE_FORMAT(addtime, '%Y-%m') as month, COUNT(*) as count " +
            "FROM xuantixinxi " +
            "WHERE addtime >= DATE_SUB(NOW(), INTERVAL 12 MONTH) " +
            "GROUP BY DATE_FORMAT(addtime, '%Y-%m') " +
            "ORDER BY month ASC"
        );
        return R.ok().put("data", result);
    }

    /**
     * 获取教师指导学生统计
     */
    @GetMapping("/teachers/student-count")
    public R getTeacherStudentCount() {
        List<Map<String, Object>> result = jdbcTemplate.queryForList(
            "SELECT j.jiaoshixingming, COUNT(x.id) as studentCount " +
            "FROM jiaoshi j " +
            "LEFT JOIN xuantixinxi x ON j.jiaoshizhanghao = x.jiaoshizhanghao " +
            "GROUP BY j.jiaoshixingming " +
            "ORDER BY studentCount DESC " +
            "LIMIT 10"
        );
        return R.ok().put("data", result);
    }

    /**
     * 获取开题报告提交统计
     */
    @GetMapping("/kaiti/statistics")
    public R getKaitiStatistics() {
        Map<String, Object> stats = new HashMap<>();

        // 已提交数量
        Integer submitted = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM kaitibaogao", Integer.class);
        stats.put("submitted", submitted != null ? submitted : 0);

        // 已通过数量
        Integer approved = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM kaitibaogao WHERE shenhejieguo = '通过'", Integer.class);
        stats.put("approved", approved != null ? approved : 0);

        // 通过率
        int sub = submitted != null ? submitted : 0;
        int app = approved != null ? approved : 0;
        double rate = sub > 0 ? (double) app / sub * 100 : 0;
        stats.put("approvalRate", Math.round(rate * 100.0) / 100.0);

        return R.ok().put("data", stats);
    }

    /**
     * 获取消息统计
     */
    @GetMapping("/messages/statistics")
    public R getMessageStatistics() {
        Map<String, Object> stats = new HashMap<>();

        // 总消息数
        Integer totalMessages = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM messages", Integer.class);
        stats.put("totalMessages", totalMessages != null ? totalMessages : 0);

        // 今日消息数
        Integer todayMessages = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM messages WHERE DATE(addtime) = CURDATE()", Integer.class);
        stats.put("todayMessages", todayMessages != null ? todayMessages : 0);

        return R.ok().put("data", stats);
    }

    /**
     * 获取Dashboard数据汇总
     */
    @IgnoreAuth
    @GetMapping("/dashboard")
    public R getDashboardData() {
        Map<String, Object> dashboard = new HashMap<>();

        // 系统概览
        dashboard.put("overview", getSystemOverview().get("data"));

        // 课题分类
        dashboard.put("topicCategories", getTopicsByCategory().get("data"));

        // 学院分布
        dashboard.put("collegeDistribution", getStudentsByCollege().get("data"));

        // 选题趋势
        dashboard.put("selectionTrend", getTopicSelectionTrend().get("data"));

        // 开题统计
        dashboard.put("kaitiStats", getKaitiStatistics().get("data"));

        return R.ok().put("data", dashboard);
    }
}
