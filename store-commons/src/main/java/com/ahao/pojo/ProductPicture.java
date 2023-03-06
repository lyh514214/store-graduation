package com.ahao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description: 商品图片
 * @Author: ahao
 * @Date: 2022/12/12 22:12
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("product_picture")
public class ProductPicture implements Serializable {

    public static final Long SerialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;                //id
    @JsonProperty("product_id")
    private Integer productId;       //商品id
    @JsonProperty("product_picture")
    private String productPicture;       //图片链接
    private String intro;             //介绍

}
