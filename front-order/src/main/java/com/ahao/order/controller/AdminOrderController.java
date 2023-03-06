package com.ahao.order.controller;

import com.ahao.order.service.OrderService;
import com.ahao.param.PageParam;
import com.ahao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 后台管理调用订单服务启动器
 * @Author: ahao
 * @Date: 2023/3/6 23:03
 **/

@RestController
@RequestMapping("order/admin")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("list")
    public R adminGetOrderList(@RequestBody PageParam pageParam){
        return orderService.adminGetOrderList(pageParam);
    }

}
