package com.ahao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 轮播图
 * @Author: ahao
 * @Date: 2022/12/11 19:46
 **/

@Data
@TableName("carousel")
public class Carousel implements Serializable {

    public static final Long SerialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @JsonProperty("carousel_id")
    private Integer carouselId;    //轮播图id
    private String imgPath;     //图片地址
    private String describes;     //描述
    @JsonProperty("product_id")
    private Integer productId;    //商品id
    private Integer priority;    //优先级

}
