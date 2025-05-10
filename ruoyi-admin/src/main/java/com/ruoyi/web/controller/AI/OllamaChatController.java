package com.ruoyi.web.controller.AI;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.system.domain.AiChatRecord;
import com.ruoyi.system.service.IAiChatRecordService;
import jakarta.annotation.Resource;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class OllamaChatController {

    @Resource
    private OllamaChatClient ollamaChatClient;
    @Resource
    private IAiChatRecordService aiChatRecordService;
    @Anonymous
    @PostMapping("/AI/AI-LLM")
    public ResponseEntity<?> ollamaChat(@RequestBody ChatRequest request) {
        try {
            // 调用大模型获取响应
            String aiContent = ollamaChatClient.call(request.getUserMsg());
            // 构建持久化记录
            AiChatRecord record = new AiChatRecord();
            record.setUserInput(request.getUserMsg());    // 用户输入（对应userInput字段）
            record.setUserId(request.getUserid());
            record.setAiResponse(aiContent);             // AI响应（对应aiResponse字段）
            record.setSessionId(request.getSessionId());
            record.setModelName("deepseek-r1:1.5b");               // 硬编码模型名称（根据实际情况调整）
            //插入数据库记录（createTime由BaseEntity自动填充）
            aiChatRecordService.insertAiChatRecord(record);


            // 构造返回数据结构
            Map<String, Object> metadata = new HashMap<>();
            metadata.put("id", UUID.randomUUID().toString()); // 生成唯一消息ID
            metadata.put("sessionId", request.getSessionId());

            Map<String, Object> aiResponse = new HashMap<>();
            aiResponse.put("content", aiContent);

            return ResponseEntity.ok(Arrays.asList(metadata, aiResponse));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    Collections.singletonMap("error", "AI服务暂时不可用")
            );
        }
    }

    // 请求参数DTO
    static class ChatRequest {
        private String userMsg;
        private String sessionId;
        private Long userid;

        // 需要getter/setter
        public String getUserMsg() { return userMsg; }
        public void setUserMsg(String userMsg) { this.userMsg = userMsg; }
        public String getSessionId() { return sessionId; }
        public void setSessionId(String sessionId) { this.sessionId = sessionId; }
        public Long getUserid(){return  userid;}
        public void setUserid(Long userid){this.userid=userid;}
    }

}