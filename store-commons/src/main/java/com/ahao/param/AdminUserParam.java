package com.ahao.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @Description: 用户登录传参
 * @Author: ahao
 * @Date: 2023/3/2 22:19
 **/
@Data
public class AdminUserParam {

    @Length(min = 6)
    private String userAccount;   //账号
    @Length(min = 6)
    private String userPassword;    //密码
    @NotBlank
    private String verCode;      //验证码

}
