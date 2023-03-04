package com.ahao.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 订单 --mq--> 商品
 * @Author: ahao
 * @Date: 2023/2/18 0:08
 **/

@Data
public class OrderToProduct implements Serializable {

    public static final Long SerialVersionUID = 1L;

    @JsonProperty("product_id")
    private Integer productId;
    private Integer num;




}
