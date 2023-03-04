package com.ahao.order.service;

import com.ahao.param.OrderParam;
import com.ahao.pojo.Order;
import com.ahao.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 订单接口
 * @Author: ahao
 * @Date: 2023/2/21 11:05
 **/
public interface OrderService extends IService<Order> {

    R save(OrderParam orderParam);

    R getOrderList(OrderParam orderParam);
}
