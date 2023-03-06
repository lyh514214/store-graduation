package com.ahao.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Description: 订单管理 出参类
 * @Author: ahao
 * @Date: 2023/3/6 22:44
 **/

@Data
public class AdminOrderVo {

    @JsonProperty("order_id")
    private Long orderId;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("product_num")
    private Integer productNum; //商品种类
    @JsonProperty("order_num")
    private Integer orderNum; //订单中商品数量
    @JsonProperty("order_price")
    private Double  orderPrice; //订单金额
    @JsonProperty("order_time")
    private Long    orderTime; //订单时间



}
