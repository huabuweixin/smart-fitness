package com.ruoyi.web.controller.AI.analy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.system.domain.AiVideoAnaly;
import com.ruoyi.system.service.IAiVideoAnalyService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@RestController
public class AiVideoController {

    @Autowired
    private RestTemplate restTemplate;
    @Resource
    private IAiVideoAnalyService aiVideoAnalyService;
    @Anonymous
    // 修改后的analyzeVideo方法
    @PostMapping(value = "/AI/AI-Anly")
    @ResponseBody
    public Map<String,String> analyzeVideo(
            @RequestParam("file") MultipartFile file,
            @RequestParam String SavePath,
            @RequestParam String mode,      // 新增模式参数
            @RequestParam(required = false) String userId) // 新增用户ID参数
            throws IllegalStateException {
        Map<String,String> resultMap = new HashMap<>();
                AiVideoAnaly record=new AiVideoAnaly();
                record.setMode(mode);
                //if(userId!=null)
                //record.setUserId(Long.valueOf(userId));
        try{
            //获取文件后缀，因此此后端代码可接收一切文件，上传格式前端限定
            String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1)
                    .toLowerCase();
            // 重构文件名称
            System.out.println("前端传递的保存路径："+SavePath);
            record.setOriginalPath(SavePath);
            String pikId = UUID.randomUUID().toString().replaceAll("-", "");
            String newVidoeName = pikId + "." + fileExt;
            System.out.println("重构文件名防止上传同名文件："+newVidoeName);
            //保存视频
            File fileSave = new File(SavePath, newVidoeName);
            file.transferTo(fileSave);
            //构造Map将视频信息返回给前端
            //正确保存视频则设置返回码为200
            String flaskUrl = "http://localhost:5000/process";
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("video_path", SavePath + "/" + newVidoeName);
            requestBody.put("mode", mode);     // 使用前端传递的模式
            try {
                ResponseEntity<String> response = restTemplate.postForEntity(
                        flaskUrl,
                        requestBody,
                        String.class
                );
                // 在发送请求后添加：
                if (response.getStatusCode().is2xxSuccessful()) {
                    // 解析Flask返回的JSON
                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, Object> flaskResponse = mapper.readValue(
                            response.getBody(),
                            new TypeReference<Map<String, Object>>() {}
                    );
                    resultMap.put("resCode","200");
                    resultMap.put("processedName",
                            flaskResponse.get("processed_path").toString());
                    System.out.println(flaskResponse.get("processed_path").toString());
                    record.setProcessedPath("../shared_storage/processed/"+flaskResponse.get("processed_path").toString());
                    //record.setStatus(flaskResponse.get("status").toString());
                }
            }catch (Exception e) {
                e.printStackTrace();
                resultMap.put("flask_status", "error");
            }
            aiVideoAnalyService.insertAiVideoAnaly(record);
            return resultMap;

        }catch (Exception e){
            e.printStackTrace();
            e.getMessage();
            //保存视频错误则设置返回码为400
            resultMap.put("resCode","400");
            return  resultMap ;
        }
    }
    // 用于接收Flask响应的内部类
    static class FlaskResponse {
        private String taskId;      // 统一驼峰命名
        private String status;
        private String processedUrl;

        // Getters and Setters
        public String getTaskId() { return taskId; }
        public void setTaskId(String taskId) { this.taskId = taskId; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getProcessedUrl() { return processedUrl; }
        public void setProcessedUrl(String processedUrl) { this.processedUrl = processedUrl; }
    }

    // 处理状态查询的GET请求
    @Anonymous
    @GetMapping("/AI/AI-Anly/{taskId}")
    public ResponseEntity<?> checkProcessingStatus(@PathVariable String taskId) {
        try {
            // 请求Flask的状态查询接口
            String flaskStatusUrl = "http://localhost:5000/status/" + taskId;
            ResponseEntity<FlaskStatusResponse> response = restTemplate.getForEntity(
                    flaskStatusUrl, FlaskStatusResponse.class);

            // 返回状态信息给前端
            return ResponseEntity.ok().body(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("查询处理状态失败: " + e.getMessage());
        }
    }

    // 用于接收Flask状态响应的内部类
    static class FlaskStatusResponse {
        private String status;
        private String processedUrl;

        // Getters and Setters
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getProcessedUrl() { return processedUrl; }
        public void setProcessedUrl(String processedUrl) { this.processedUrl = processedUrl; }
    }
}
