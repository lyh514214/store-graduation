package com.ahao.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Description: UserLoginParam
 * @Author: ahao
 * @Date: 2022/12/5 2:18
 **/
@Data
public class UserLoginParam {

    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
