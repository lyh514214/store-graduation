package com.ahao.order.mapper;

import com.ahao.pojo.Order;
import com.ahao.vo.AdminOrderVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description: order的数据库操作接口
 * @Author: ahao
 * @Date: 2023/2/21 11:14
 **/

public interface OrderMapper extends BaseMapper<Order> {
    List<AdminOrderVo> selectAdminOrders(@Param("offset") int offset,@Param("number") int number);
}
