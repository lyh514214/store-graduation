package com.ahao.admin.config;

import com.ahao.admin.interceptor.LoginProtectInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: mvc配置类
 * @Author: ahao
 * @Date: 2023/3/3 10:32
 **/

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    //拦截全部 放行部分
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginProtectInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/","/index.html","/index","/static/**",
                        "/user/login", "/user/logout",
                        "/api/**", "/css/**", "/images/**",
                        "/js/**", "/lib/**","/captcha",
                        "/user/list"
                );
    }
}
