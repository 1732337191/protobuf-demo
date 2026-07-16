package com.ltgame.protobufdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author Jari
 * @version 1.0
 * @description: TODO
 * @date 2026/7/16 13:48
 */
@Configuration
public class ProtobufConfig implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 添加 Protobuf 转换器（放在最前面优先匹配）
        converters.add(0, new ProtobufHttpMessageConverter());
        // 保留默认的 JSON 转换器，实现双格式支持
        converters.add(new MappingJackson2HttpMessageConverter());
    }
}
