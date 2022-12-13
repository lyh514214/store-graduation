package com.ahao.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * TODO 要使用jsr 303注解进行参数校验
 *
 * @Description 接收前端用户参数
 * @Author ahao
 * @Date 2022/12/4 23:00
 **/

@Data
public class UserCheckParam {

    @NotBlank
    private String userName;  //注意：这个参数名称要和前端(json key)传回来的参数名称一致
}


/*
* 拓展:
@NotBlank 字符串  不能为null 和 ""
@NotNull  字符串  不能为null
@NotEmpty 集合   长度不能为0
* */
