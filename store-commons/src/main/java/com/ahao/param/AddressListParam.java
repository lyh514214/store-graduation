package com.ahao.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description: AddressListParam
 * @Author: ahao
 * @Date: 2022/12/5 11:18
 **/
@Data
public class AddressListParam {

    @NotNull
    @JsonProperty("user_id")
    private Integer userId;

}
