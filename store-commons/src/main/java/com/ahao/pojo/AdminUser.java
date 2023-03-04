package com.ahao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 管理员用户实体类
 * @Author: ahao
 * @Date: 2023/3/2 22:24
 **/

@Data
@TableName("admin_user")
public class AdminUser implements Serializable {

    public static final Long SerialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private String userId;

    private String userName;   //用户名
    private String userAccount;    //账号
    private String userPassword;    //密码
    private String userPhone;     //电话
    private Date createTime;      //创建时间
    private Integer userRole;     //用户角色

}
