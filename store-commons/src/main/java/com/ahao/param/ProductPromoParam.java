package com.ahao.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description: 商品类别名称
 * 传入参数
 * @Author: ahao
 * @Date: 2022/12/13 1:01
 **/
@Data
public class ProductPromoParam {

    @NotNull
    private String categoryName;
}
