package com.ahao.user.controller;

import com.ahao.param.UserCheckParam;
import com.ahao.param.UserLoginParam;
import com.ahao.pojo.User;
import com.ahao.user.service.UserService;
import com.ahao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 用户控制器
 * @Author ahao
 * @Date 2022/12/4 23:16
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @Description: 账号检查
     * @param userCheckParam 前端传回的账号参数; 注解@RequestBody ==> 接收前端传回来的json参数; @Validated==>使UserCheckParam里的判空注解生效;
     * @param result BindingResult获取校验结果，必须紧挨着需校验参数
     * @return com.ahao.utils.R
    **/
    @PostMapping("check")
    public R userCheck(@RequestBody @Validated UserCheckParam userCheckParam, BindingResult result){

        boolean hasErrors = result.hasErrors();  //是否满足校验规则
        if (hasErrors){
            return R.fail("账号为空，不可使用!");
        }
        return userService.check(userCheckParam);
    }

    /**
     * @Description: 账号注册
     * @param user 账号、密码、电话
     * @param result 参数校验
     * @return com.ahao.utils.R
    **/
    @PostMapping("register")
    public R userRegister(@RequestBody @Validated User user,BindingResult result){

        if (result.hasErrors()){
            return R.fail("参数异常，不可注册！");
        }
        return userService.register(user);
    }

    /**
     * @Description: 用户登录
     * @param userLoginParam 账号、密码
     * @param result 参数校验
     * @return com.ahao.utils.R、 用户id、用户账号
    **/
    @PostMapping("login")
    public R userLogin(@RequestBody @Validated UserLoginParam userLoginParam,BindingResult result){

        if (result.hasErrors()){
            return R.fail("参数异常，操作失败");
        }
        return userService.login(userLoginParam);
    }


}
