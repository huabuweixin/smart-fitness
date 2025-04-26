package com.ruoyi.framework.config;

import java.util.TimeZone;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import com.fasterxml.jackson.core.JsonParser; // 新增导入

/**
 * 程序注解配置
 *
 * @author ruoyi
 */
@Configuration
@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan("com.ruoyi.**.mapper")
public class ApplicationConfig
{
    /**
     * 时区配置 + 允许JSON注释
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization()
    {
        return jacksonObjectMapperBuilder ->
                jacksonObjectMapperBuilder
                        // 设置默认时区
                        .timeZone(TimeZone.getDefault())
                        // 允许JSON注释（如：// 或 /* */）
                        .featuresToEnable(JsonParser.Feature.ALLOW_COMMENTS); // 新增配置
    }
}
