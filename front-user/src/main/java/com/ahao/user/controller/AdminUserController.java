package com.ahao.user.controller;

import com.ahao.param.PageParam;
import com.ahao.param.UserIdParam;
import com.ahao.pojo.User;
import com.ahao.user.service.UserService;
import com.ahao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 后台管理模块调用用户模块
 * @Author: ahao
 * @Date: 2023/3/3 16:11
 **/

@RestController
@RequestMapping("/user/admin")
public class AdminUserController {

    @Autowired
    private UserService userService;

    /**
     * @Description: 后台管理模块调用用户模块  ==》 查看用户列表（分页）
     **/
    @GetMapping("list")
    public R adminGetUsersByPage(@RequestBody PageParam pageParam){
        return userService.adminGetUsersByPage(pageParam);
    }

    /**
     * @Description: 后台管理模块调用用户模块 ==》 添加用户
    **/
    @PostMapping("save")
    public R adminSaveUser(@RequestBody User user){
        return userService.adminSaveUser(user);
    }

    /**
     * @Description: 后台管理模块调用用户模块 ==》 删除用户
    **/
    @PostMapping("remove")
    public R adminRemoveUser(@RequestBody UserIdParam userIdParam){
        return userService.adminRemoveUser(userIdParam);
    }

    /**
     * @Description: 后台管理模块调用用户模块  ==》  修改用户
    **/
    @PostMapping("update")
    public R userUpdate(@RequestBody User user){
        return userService.updateUserByAdmin(user);
    }




}
