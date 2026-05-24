package com.controller;

import com.entity.AiAgentRecommendationEntity;
import com.service.AiAgentRecommendationService;
import com.utils.PageUtils;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * AI智能推荐控制器
 */
@RestController
@RequestMapping("/aiagent/recommendation")
public class AiAgentRecommendationController {

    @Autowired
    private AiAgentRecommendationService recommendationService;

    /**
     * 生成课题推荐
     */
    @PostMapping("/generate/topics")
    public R generateTopicRecommendations(@RequestParam Long studentId) {
        List<AiAgentRecommendationEntity> recommendations = 
            recommendationService.generateTopicRecommendations(studentId);
        return R.ok().put("recommendations", recommendations);
    }

    /**
     * 生成导师推荐
     */
    @PostMapping("/generate/teachers")
    public R generateTeacherRecommendations(@RequestParam Long studentId) {
        List<AiAgentRecommendationEntity> recommendations = 
            recommendationService.generateTeacherRecommendations(studentId);
        return R.ok().put("recommendations", recommendations);
    }

    /**
     * 获取用户推荐列表
     */
    @GetMapping("/list")
    public R getUserRecommendations(@RequestParam Long userId,
                                     @RequestParam String userType,
                                     @RequestParam(required = false, defaultValue = "1") Integer page,
                                     @RequestParam(required = false, defaultValue = "10") Integer limit) {
        Map<String, Object> params = new java.util.HashMap<>();
        params.put("page", page.toString());
        params.put("limit", limit.toString());
        
        PageUtils pageUtils = recommendationService.getUserRecommendations(userId, userType, params);
        return R.ok().put("page", pageUtils);
    }

    /**
     * 标记推荐为已读
     */
    @PostMapping("/markRead/{id}")
    public R markAsRead(@PathVariable Long id) {
        recommendationService.markAsRead(id);
        return R.ok();
    }

    /**
     * 列表查询
     */
    @GetMapping("/all")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = recommendationService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 详情查询
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable Long id) {
        AiAgentRecommendationEntity recommendation = recommendationService.selectById(id);
        return R.ok().put("recommendation", recommendation);
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        recommendationService.deleteBatchIds(java.util.Arrays.asList(ids));
        return R.ok();
    }
}
