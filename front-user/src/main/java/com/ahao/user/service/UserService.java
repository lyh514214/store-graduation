package com.ahao.user.service;

import com.ahao.param.PageParam;
import com.ahao.param.UserCheckParam;
import com.ahao.param.UserIdParam;
import com.ahao.param.UserLoginParam;
import com.ahao.pojo.User;
import com.ahao.utils.R;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Description: 用户业务实现接口
 * @Author: ahao
 * @Date: 2022/12/5 0:41
 **/
public interface UserService {

    /**
     * @Description: 检查账号是否有用业务
     * @param userCheckParam 传入的账号
     * @return com.ahao.utils.R
    **/
    R check(UserCheckParam userCheckParam);

    /**
     * @Description: 账号注册
     * @param user 账号、密码、电话信息
     * @return com.ahao.utils.R
    **/
    R register(User user);

    /**
     * @Description: 用户登录
     * @param userLoginParam 账号、密码信息
     * @return com.ahao.utils.R + 用户id、账号
    **/
    R login(UserLoginParam userLoginParam);

    /**
     * @Description: 后台管理模块调用用户模块
    **/
    R adminGetUsersByPage(PageParam pageParam);

    R adminSaveUser(User user);

    R adminRemoveUser(UserIdParam userIdParam);

    R updateUserByAdmin(User user);
}

