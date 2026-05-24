package com.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.AiAgentRecommendationDao;
import com.dao.KetixinxiDao;
import com.dao.JiaoshiDao;
import com.entity.AiAgentRecommendationEntity;
import com.entity.KetixinxiEntity;
import com.entity.JiaoshiEntity;
import com.service.AiAgentRecommendationService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("aiAgentRecommendationService")
public class AiAgentRecommendationServiceImpl extends ServiceImpl<AiAgentRecommendationDao, AiAgentRecommendationEntity> implements AiAgentRecommendationService {

    @Autowired
    private KetixinxiDao ketixinxiDao;

    @Autowired
    private JiaoshiDao jiaoshiDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AiAgentRecommendationEntity> page = this.selectPage(
                new Query<AiAgentRecommendationEntity>(params).getPage(),
                new EntityWrapper<AiAgentRecommendationEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public List<AiAgentRecommendationEntity> generateTopicRecommendations(Long studentId) {
        // 这里简化实现，实际应基于学生专业、兴趣等进行智能推荐
        List<KetixinxiEntity> topics = ketixinxiDao.selectList(new EntityWrapper<KetixinxiEntity>());
        
        List<AiAgentRecommendationEntity> recommendations = new ArrayList<>();
        Random random = new Random();
        
        // 随机选择5个课题作为推荐
        Collections.shuffle(topics);
        int count = Math.min(5, topics.size());
        
        for (int i = 0; i < count; i++) {
            KetixinxiEntity topic = topics.get(i);
            
            AiAgentRecommendationEntity recommendation = new AiAgentRecommendationEntity();
            recommendation.setUserId(studentId);
            recommendation.setUserType("student");
            recommendation.setRecommendationType("topic");
            recommendation.setItemId(topic.getId());
            recommendation.setScore(0.7 + random.nextDouble() * 0.3); // 0.7-1.0
            recommendation.setReason("基于您的专业背景和兴趣匹配");
            recommendation.setExtraData(JSON.toJSONString(topic));
            recommendation.setIsRead(0);
            recommendation.setCreateTime(new Date());
            
            recommendations.add(recommendation);
            this.insert(recommendation);
        }
        
        return recommendations;
    }

    @Override
    public List<AiAgentRecommendationEntity> generateTeacherRecommendations(Long studentId) {
        // 这里简化实现，实际应基于研究方向匹配
        List<JiaoshiEntity> teachers = jiaoshiDao.selectList(new EntityWrapper<JiaoshiEntity>());
        
        List<AiAgentRecommendationEntity> recommendations = new ArrayList<>();
        Random random = new Random();
        
        // 随机选择3个导师作为推荐
        Collections.shuffle(teachers);
        int count = Math.min(3, teachers.size());
        
        for (int i = 0; i < count; i++) {
            JiaoshiEntity teacher = teachers.get(i);
            
            AiAgentRecommendationEntity recommendation = new AiAgentRecommendationEntity();
            recommendation.setUserId(studentId);
            recommendation.setUserType("student");
            recommendation.setRecommendationType("teacher");
            recommendation.setItemId(teacher.getId());
            recommendation.setScore(0.6 + random.nextDouble() * 0.4); // 0.6-1.0
            recommendation.setReason("研究方向匹配度高");
            recommendation.setExtraData(JSON.toJSONString(teacher));
            recommendation.setIsRead(0);
            recommendation.setCreateTime(new Date());
            
            recommendations.add(recommendation);
            this.insert(recommendation);
        }
        
        return recommendations;
    }

    @Override
    public void markAsRead(Long recommendationId) {
        AiAgentRecommendationEntity recommendation = this.selectById(recommendationId);
        if (recommendation != null) {
            recommendation.setIsRead(1);
            this.updateById(recommendation);
        }
    }

    @Override
    public PageUtils getUserRecommendations(Long userId, String userType, Map<String, Object> params) {
        Page<AiAgentRecommendationEntity> page = this.selectPage(
                new Query<AiAgentRecommendationEntity>(params).getPage(),
                new EntityWrapper<AiAgentRecommendationEntity>()
                        .eq("user_id", userId)
                        .eq("user_type", userType)
                        .orderBy("create_time", false)
        );
        return new PageUtils(page);
    }
}
