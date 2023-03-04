package com.ahao.order.controller;

import com.ahao.order.service.OrderService;
import com.ahao.param.OrderParam;
import com.ahao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 订单控制器
 * @Author: ahao
 * @Date: 2023/2/21 11:01
 **/

@RequestMapping("order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * @Description: 订单保存
    **/
    @PostMapping("save")
    public R save(@RequestBody OrderParam orderParam){
        return orderService.save(orderParam);
    }

    /**
     * @Description: 订单查询
    **/
    @PostMapping("list")
    public R getOrderList(@RequestBody OrderParam orderParam){
        return orderService.getOrderList(orderParam);
    }
}
