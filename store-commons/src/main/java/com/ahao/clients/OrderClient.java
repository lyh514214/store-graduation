package com.ahao.clients;

import com.ahao.param.PageParam;
import com.ahao.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description: 订单客户端调用
 * @Author: ahao
 * @Date: 2023/3/6 23:00
 **/

@FeignClient("order-server")
public interface OrderClient {

    @GetMapping("order/admin/list")
    R adminGetOrderList(@RequestBody PageParam pageParam);


}
