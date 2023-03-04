package com.ahao.param;

import com.ahao.pojo.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 地址保存参数
 * @Author: ahao
 * @Date: 2023/2/23 22:31
 **/

@Data
public class AddressSaveParam extends Address implements Serializable{

    public static final Long SerialVersionUID = 1L;

    @JsonProperty("user_id")
    private Integer userId;
}
