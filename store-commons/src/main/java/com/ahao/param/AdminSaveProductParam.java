package com.ahao.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @Description: 管理模块保存商品信息需要用到的参数
 * @Author: ahao
 * @Date: 2023/3/6 16:04
 **/

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminSaveProductParam{


    private String pictures;  //商品图片
    private String productIntro;   //商品描述

    private Integer productId;   //商品id
    private String productName;   //商品名称
    private Integer categoryId;    //类别
    private String productTitle;     //商品提示
    private String productPicture;    //商品图片
    private Double productPrice;     //商品价钱
    private Double productSellingPrice;     //商品售价
    private Integer productNum;        //商品数量
    private Integer productSales;       //商品销量


}
