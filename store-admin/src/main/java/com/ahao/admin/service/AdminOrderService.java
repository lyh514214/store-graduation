package com.ahao.admin.service;

import com.ahao.param.PageParam;
import com.ahao.utils.R;

/**
 * @Description: 订单管理接口
 * @Author: ahao
 * @Date: 2023/3/6 22:57
 **/
public interface AdminOrderService {
    R adminGetOrderList(PageParam pageParam);
}
