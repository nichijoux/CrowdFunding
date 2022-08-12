package com.zh.crowd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrowdWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 添加view-controller
        registry.addViewController("/toLoginPage").setViewName("member/login");
        registry.addViewController("/toRegisterPage").setViewName("member/register");
        registry.addViewController("/toCenterPage").setViewName("member/center");
        registry.addViewController("/toMyCrowdPage").setViewName("member/crowd");
    }
}
