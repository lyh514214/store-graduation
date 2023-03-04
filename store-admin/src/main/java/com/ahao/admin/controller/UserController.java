package com.ahao.admin.controller;

import com.ahao.admin.service.AdminUserService;
import com.ahao.param.AdminUserParam;
import com.ahao.param.PageParam;
import com.ahao.param.UserIdParam;
import com.ahao.pojo.AdminUser;
import com.ahao.pojo.User;
import com.ahao.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

/**
 * @Description: 用户登录控制器
 * @Author: ahao
 * @Date: 2023/3/2 22:35
 **/

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private AdminUserService adminUserService;

    /**
     * @Description: 后台登录校验
    **/
    @PostMapping("/login")
    public R login(@Validated AdminUserParam adminUserParam, BindingResult result, HttpSession session){
        if (result.hasErrors()){
            return R.fail("参数有误！");
        }
        String verCode = adminUserParam.getVerCode();
        String captcha = (String) session.getAttribute("captcha");
        if (!verCode.equalsIgnoreCase(captcha)){
            return R.fail("验证码有误，请重新输入！");
        }
        R r = adminUserService.login(adminUserParam);
        AdminUser adminUser = (AdminUser) r.getData();
        session.setAttribute("userInfo",adminUser);
        return r;
    }

    /**
     * @Description: 退出登录
    **/
    @GetMapping("/logout")
    public R logout(HttpSession session){
        session.invalidate();
        log.info("退出登录！");
        return R.ok("退出登录成功！");
    }

    /**
     * @Description: 用户列表展示
    **/
    @GetMapping("/list")
    public R userList(PageParam pageParam){
        return adminUserService.getUsersByPage(pageParam);
    }

    /**
     * @Description: 用户添加
    **/
    @PostMapping("/save")
    public R userAdd(User user){
        return adminUserService.userSave(user);
    }

    /**
     * @Description: 删除用户
    **/
    @PostMapping("/remove")
    public R userDelete(UserIdParam userIdParam){
        return adminUserService.removeUserById(userIdParam);
    }

    /**
     * @Description: 修改用户
    **/
    @PostMapping("/update")
    public R userUpdate(User user){
        return adminUserService.userUpdate(user);
    }






}
