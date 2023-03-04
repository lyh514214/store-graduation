package com.ahao.admin.service;

import com.ahao.param.AdminUserParam;
import com.ahao.param.PageParam;
import com.ahao.param.UserIdParam;
import com.ahao.pojo.User;
import com.ahao.utils.R;

import java.util.List;

/**
 * @Description: 管理员登录接口
 * @Author: ahao
 * @Date: 2023/3/2 22:57
 **/

public interface AdminUserService {

    R login(AdminUserParam adminUserParam);

    R getUsersByPage(PageParam pageParam);

    R userSave(User user);

    R removeUserById(UserIdParam userIdParam);

    R userUpdate(User user);
}
