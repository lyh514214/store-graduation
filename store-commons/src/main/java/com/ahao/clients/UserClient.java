package com.ahao.clients;

import com.ahao.param.PageParam;
import com.ahao.param.UserIdParam;
import com.ahao.pojo.User;
import com.ahao.utils.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @Description: 用户客户端
 * @Author: ahao
 * @Date: 2023/3/3 10:51
 **/

@FeignClient("user-server")
public interface UserClient {

    /**
     * @Description: 查看用户列表（分页） 后台管理模块调用用户模块
    **/
    @GetMapping("user/admin/list")
    R adminGetUses(@RequestBody PageParam pageParam);

    /**
     * @Description: 添加用户  后台管理模块调用用户模块
    **/
    @PostMapping("user/admin/save")
    R adminSaveUser(@RequestBody User user);

    /**
     * @Description: 删除用户 后台管理模块调用用户模块
    **/
    @PostMapping("user/admin/remove")
    R removeUserById(@RequestBody UserIdParam userIdParam);

    /**
     * @Description: 修改用户  后台管理模块调用用户模块
     * @return com.ahao.utils.R
    **/
    @PostMapping("user/admin/update")
    R userUpdate(@RequestBody User user);

}
