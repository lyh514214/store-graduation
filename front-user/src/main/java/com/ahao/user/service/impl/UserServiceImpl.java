package com.ahao.user.service.impl;

import com.ahao.constants.UserConstants;
import com.ahao.param.UserCheckParam;
import com.ahao.param.UserLoginParam;
import com.ahao.pojo.User;
import com.ahao.user.mapper.UserMapper;
import com.ahao.user.service.UserService;
import com.ahao.utils.MD5Util;
import com.ahao.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 用户业务实现类
 * @Author: ahao
 * @Date: 2022/12/5 0:44
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * @param userCheckParam 传入的账号
     * @return com.ahao.utils.R
     * @Description: 检查账号是否有用业务
     **/
    @Override
    public R check(UserCheckParam userCheckParam) {

        // 1.参数封装
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userCheckParam.getUserName());
        // 2.数据库查询
        Long count = userMapper.selectCount(queryWrapper);
        // 3.结果处理
        if (count == 0){
            log.info("UserServiceImpl.check业务结束，结果为:{}","账号可以使用！");
            return R.ok("用户名不存在，可以注册");
        }
        log.info("UserServiceImpl.check业务结束，结果为:{}", "账号不可使用！");
        return R.fail("用户名已存在，注册失败");
    }

    /**
     * @param user 账号、密码、电话信息
     * @return com.ahao.utils.R
     * @Description: 账号注册
     **/
    @Override
    public R register(User user) {
        // 1.参数处理
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",user.getUserName());
        // 2.数据库处理
        Long count = userMapper.selectCount(queryWrapper);
        // 3.结果处理
        if (count > 0){
            log.info("UserServiceImpl.register业务结束，结果为:{}","账号已存在！");
            return R.fail("注册失败，账号已存在");
        }
        // 3.1 密码加密处理  采用MD5+加盐
        String newPwd = MD5Util.encode(user.getPassword() + UserConstants.USER_SLAT);
        user.setPassword(newPwd);
        int insert = userMapper.insert(user);
        if (insert == 0){
            log.info("UserServiceImpl.register业务结束，结果为:{}","账号注册失败，持久层插入数据失败！");
            return R.fail("抱歉，账号注册失败");
        }
        log.info("UserServiceImpl.register业务结束，结果为:{}","账号成功注册！");
        return R.ok("注册成功");
    }

    /**
     * @param userLoginParam 账号、密码信息
     * @return com.ahao.utils.R + 用户id、账号
     * @Description: 用户登录
     **/
    @Override
    public R login(UserLoginParam userLoginParam) {
        // 1.密码加密加盐处理
        String newPwd = MD5Util.encode(userLoginParam.getPassword() + UserConstants.USER_SLAT);
        // 2.账号密码对比数据库
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userLoginParam.getUserName());
        queryWrapper.eq("password",newPwd);
        User user = userMapper.selectOne(queryWrapper);
        // 3.判断返回结果
        if (user == null){
            log.info("UserServiceImpl.login业务结束，结果为:{}","账号或密码错误");
            return R.fail("账号或密码错误");
        }
        user.setPassword(null);
        user.setUserPhonenumber(null);
        log.info("UserServiceImpl.login业务结束，结果为:{}","登录成功！");
        return R.ok("登录成功！",user);
    }

}
