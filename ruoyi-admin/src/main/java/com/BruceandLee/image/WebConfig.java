package com.BruceandLee.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${ruoyi.profile}")
    private String uploadDir; // 示例：D:\\ruoyi-uploads

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Windows 路径需转义反斜杠或使用正斜杠
        String winPath = "file:" + uploadDir.replace("\\", "/") + "/image/";
        registry.addResourceHandler("/image/**")
                .addResourceLocations(winPath);
    }
}
