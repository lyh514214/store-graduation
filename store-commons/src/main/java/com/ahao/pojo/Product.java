package com.ahao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description: 商品
 * @Author: ahao
 * @Date: 2022/12/12 21:36
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("product")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable {

    public static final Long SerialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @JsonProperty("product_id")
    private Integer productId;   //商品id

    @JsonProperty("product_name")
    private String productName;   //商品名称
    @JsonProperty("category_id")
    private Integer categoryId;    //类别
    @JsonProperty("product_title")
    private String productTitle;     //商品提示
    @JsonProperty("product_intro")
    private String productIntro;     //商品介绍
    @JsonProperty("product_picture")
    private String productPicture;    //商品图片
    @JsonProperty("product_price")
    private Double productPrice;     //商品价钱
    @JsonProperty("product_selling_price")
    private Double productSellingPrice;     //商品售价
    @JsonProperty("product_num")
    private Integer productNum;        //商品数量
    @JsonProperty("product_sales")
    private Integer productSales;       //商品销量










}
