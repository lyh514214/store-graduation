package com.ahao.admin.service.impl;

import com.ahao.admin.service.AdminOrderService;
import com.ahao.clients.OrderClient;
import com.ahao.param.PageParam;
import com.ahao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 订单管理实现类
 * @Author: ahao
 * @Date: 2023/3/6 22:57
 **/

@Service
public class AdminOrderServiceImpl implements AdminOrderService {

    @Autowired
    private OrderClient orderClient;

    /**
     * @Description: 后台订单管理 - 订单列表查询
    **/
    @Override
    public R adminGetOrderList(PageParam pageParam) {
        return orderClient.adminGetOrderList(pageParam);
    }
}
