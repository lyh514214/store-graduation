package com.ahao.admin.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 登录安全拦截器
 * @Author: ahao
 * @Date: 2023/3/3 10:24
 **/

@Component
@Slf4j
public class LoginProtectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object userInfo = request.getSession().getAttribute("userInfo");

        if (userInfo != null){
            return true;
        }
        response.sendRedirect((request.getContextPath()+"/index.html"));
        log.info("在后台管理的操作已被拦截！");
        //返回true放行 false拦截
        return false;
    }
}
