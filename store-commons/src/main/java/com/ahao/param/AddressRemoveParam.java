package com.ahao.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description: AddressRemoveParam
 * @Author: ahao
 * @Date: 2022/12/5 13:40
 **/
@Data
public class AddressRemoveParam {

    @NotNull
    private Integer id;
}
