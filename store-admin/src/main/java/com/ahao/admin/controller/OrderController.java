package com.ahao.admin.controller;

import com.ahao.admin.service.AdminOrderService;
import com.ahao.param.PageParam;
import com.ahao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 订单管理控制器
 * @Author: ahao
 * @Date: 2023/3/6 22:54
 **/

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private AdminOrderService adminOrderService;

    /**
     * @Description: 查询订单列表
    **/
    @GetMapping("list")
    public R adminGetOrderList(PageParam pageParam){
        return adminOrderService.adminGetOrderList(pageParam);
    }
}
