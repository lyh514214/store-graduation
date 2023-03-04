package com.ahao.vo;

import com.ahao.pojo.Order;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Description: 订单传参类
 * @Author: ahao
 * @Date: 2023/2/21 21:21
 **/

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderVo extends Order {

    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("product_picture")
    private String productPicture;

}
