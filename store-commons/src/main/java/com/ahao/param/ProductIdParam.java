package com.ahao.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description: 单一商品id参数类
 * @Author: ahao
 * @Date: 2023/2/15 17:08
 **/

@Data
public class ProductIdParam {

    @NotNull
    public Integer productId;
}
