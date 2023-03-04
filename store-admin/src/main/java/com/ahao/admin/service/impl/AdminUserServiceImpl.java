package com.ahao.admin.service.impl;

import com.ahao.admin.mapper.AdminUserMapper;
import com.ahao.admin.service.AdminUserService;
import com.ahao.clients.UserClient;
import com.ahao.constants.UserConstants;
import com.ahao.param.AdminUserParam;
import com.ahao.param.PageParam;
import com.ahao.param.UserIdParam;
import com.ahao.pojo.AdminUser;
import com.ahao.pojo.User;
import com.ahao.utils.MD5Util;
import com.ahao.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 管理员登录接口实现类
 * @Author: ahao
 * @Date: 2023/3/2 22:58
 **/

@Service
@Slf4j
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;
    @Autowired
    private UserClient userClient;

    /**
     * @Description: 登录业务
    **/
    @Override
    public R login(AdminUserParam adminUserParam) {
        String userAccount = adminUserParam.getUserAccount();
        String password = adminUserParam.getUserPassword();

        String userPassword = MD5Util.encode(password + UserConstants.USER_SLAT);  //加密处理

        QueryWrapper<AdminUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account",userAccount);
        queryWrapper.eq("user_password",userPassword);
        AdminUser adminUser = adminUserMapper.selectOne(queryWrapper);
        if (adminUser == null){
            return R.fail("密码或者账户错误！");
        }
        log.info("管理员登录业务结束！");
        return R.ok("用户登录成功！",adminUser);
    }


    /**
     * @Description: 获取用户列表业务
     * */
    @Cacheable(value = "admin.userList",key = "#pageParam.currentPage+'-'+#pageParam.pageSize")   //缓存
    @Override
    public R getUsersByPage(PageParam pageParam) {
        R r = userClient.adminGetUses(pageParam);
        log.info("加载用户列表业务完成，结果为{}条",r.getTotal());
        return r;
    }

    /**
     * @Description: 添加新的用户
    **/
    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "admin.userList",allEntries = true)
            }
    )  //缓存
    public R userSave(User user) {
        R r = userClient.adminSaveUser(user);
        log.info("用户添加业务已完成！");
        return r;
    }

    /**
     * @Description: 通过id删除用户
    **/
    @Caching(
            evict = {
                    @CacheEvict(value = "admin.userList",allEntries = true)
            }
    )
    @Override  //缓存
    public R removeUserById(UserIdParam userIdParam) {
        R r = userClient.removeUserById(userIdParam);
        log.info("用户删除业务已完成！");
        return r;
    }

    /**
     * @Description: 修改用户信息
    **/
    @Caching(
            evict = {@CacheEvict(value = "admin.userList",allEntries = true)}
    )
    @Override  //缓存
    public R userUpdate(User user) {
        R r = userClient.userUpdate(user);
        log.info("用户修改业务已完成！");
        return r;
    }


}
