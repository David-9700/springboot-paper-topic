package com.controller;

import com.annotation.IgnoreAuth;
import com.entity.AiAgentChatEntity;
import com.service.AiAgentChatService;
import com.utils.PageUtils;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * AI智能助手控制器
 */
@RestController
@RequestMapping("/aiagent/chat")
public class AiAgentChatController {

    @Autowired
    private AiAgentChatService aiAgentChatService;

    /**
     * 发送消息并获取AI回复
     */
    @PostMapping("/send")
    public R sendMessage(@RequestBody Map<String, Object> params) {
        Long userId = Long.valueOf(params.get("userId").toString());
        String userType = params.get("userType").toString();
        String sessionId = params.containsKey("sessionId") ? params.get("sessionId").toString() : null;
        String message = params.get("message").toString();

        Map<String, Object> result = aiAgentChatService.sendMessage(userId, userType, sessionId, message);
        return R.ok().put("data", result);
    }

    /**
     * 获取会话历史
     */
    @GetMapping("/history")
    public R getSessionHistory(@RequestParam String sessionId,
                                @RequestParam(required = false, defaultValue = "1") Integer page,
                                @RequestParam(required = false, defaultValue = "20") Integer limit) {
        Map<String, Object> params = new java.util.HashMap<>();
        params.put("page", page.toString());
        params.put("limit", limit.toString());
        
        PageUtils pageUtils = aiAgentChatService.getSessionHistory(sessionId, params);
        return R.ok().put("page", pageUtils);
    }

    /**
     * 清除会话
     */
    @DeleteMapping("/session/{sessionId}")
    public R clearSession(@PathVariable String sessionId) {
        aiAgentChatService.clearSession(sessionId);
        return R.ok();
    }

    /**
     * 列表查询
     */
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = aiAgentChatService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 详情查询
     */
    @GetMapping("/info/{id}")
    public R info(@PathVariable Long id) {
        AiAgentChatEntity chat = aiAgentChatService.selectById(id);
        return R.ok().put("chat", chat);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody AiAgentChatEntity chat) {
        aiAgentChatService.insert(chat);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody AiAgentChatEntity chat) {
        aiAgentChatService.updateById(chat);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        aiAgentChatService.deleteBatchIds(java.util.Arrays.asList(ids));
        return R.ok();
    }
}
