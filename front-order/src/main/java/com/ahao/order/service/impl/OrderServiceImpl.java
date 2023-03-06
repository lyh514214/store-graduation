package com.ahao.order.service.impl;

import com.ahao.clients.ProductClient;
import com.ahao.order.mapper.OrderMapper;
import com.ahao.order.service.OrderService;
import com.ahao.param.OrderParam;
import com.ahao.param.PageParam;
import com.ahao.param.RealProductIdsParam;
import com.ahao.pojo.Order;
import com.ahao.pojo.Product;
import com.ahao.to.OrderToProduct;
import com.ahao.utils.R;
import com.ahao.vo.AdminOrderVo;
import com.ahao.vo.CartVo;
import com.ahao.vo.OrderVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 订单实现类
 * @Author: ahao
 * @Date: 2023/2/21 11:06
 **/

@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ProductClient productClient;

    /**
     * @Description: 订单保存
     * 1、将购物车数据转为订单数据
     * 2、进行订单数据的批量插入
     * 3、商品库存修改信息
     * 4、发送购物车库存修改信息
     * @return com.ahao.utils.R
    **/
    @Override
    @Transactional
    public R save(OrderParam orderParam) {

        //准备数据
        ArrayList<Integer> cartIds = new ArrayList<>();
        ArrayList<OrderToProduct> orderToProducts = new ArrayList<>();
        ArrayList<Order> orderList = new ArrayList<>();

        //生成数据
        Integer userId = orderParam.getUserId();
        long orderId = System.currentTimeMillis();
        List<CartVo> products = orderParam.getProducts();

        for (CartVo cartVo : products) {
            cartIds.add(cartVo.getId());  //保存要删除的购物车项id
            OrderToProduct orderToProduct = new OrderToProduct();
            orderToProduct.setProductId(cartVo.getProductId());
            orderToProduct.setNum(cartVo.getNum());
            orderToProducts.add(orderToProduct);   //保存要删除商品库存的数据
            Order order = new Order();
            order.setOrderId(orderId);
            order.setOrderTime(orderId);
            order.setUserId(userId);
            order.setProductId(cartVo.getProductId());
            order.setProductNum(cartVo.getNum());
            order.setProductPrice(cartVo.getPrice());
            orderList.add(order);     //订单集合封装
        }

        //订单数据批量保存
        boolean saveBatch = saveBatch(orderList);

        //发送购物车信息
        rabbitTemplate.convertAndSend("topic.ex","clear.cart",cartIds);
        //发送商品信息
        rabbitTemplate.convertAndSend("topic.ex","sub.number",orderToProducts);

        return R.ok("订单生成成功！");
    }

    @Override
    public R getOrderList(OrderParam orderParam) {

        //查询用户订单数据
        Integer userId = orderParam.getUserId();
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<Order> orderList = this.list(queryWrapper);

        //分组
        Map<Long, List<Order>> orderMap = orderList.stream().collect(Collectors.groupingBy(Order::getOrderId));

        //获取商品信息
        List<Integer> productIdList = orderList.stream().map(Order::getProductId).collect(Collectors.toList());
        RealProductIdsParam realProductIdsParam = new RealProductIdsParam();
        realProductIdsParam.setProductIds(productIdList);
        List<Product> productList = productClient.orderGetProducts(realProductIdsParam);

        //将商品集合转化为map  (productId product)
        Map<Integer, Product> productMap = productList.stream().collect(Collectors.toMap(Product::getProductId, v -> v));

        //结果收集对象
        List<List<OrderVo>> result = new ArrayList<>();

        for (List<Order> orders : orderMap.values()) {
            ArrayList<OrderVo> orderVos = new ArrayList<>();
            for (Order order : orders) {
                OrderVo orderVo = new OrderVo();
                Product product = productMap.get(order.getProductId());
                orderVo.setId(order.getId());
                orderVo.setOrderId(order.getOrderId());
                orderVo.setOrderTime(order.getOrderTime());
                orderVo.setUserId(order.getUserId());
                orderVo.setProductId(order.getProductId());
                orderVo.setProductNum(order.getProductNum());
                orderVo.setProductPrice(order.getProductPrice());
                orderVo.setProductName(product.getProductName());
                orderVo.setProductPicture(product.getProductPicture());
                orderVos.add(orderVo);
            }
            result.add(orderVos);
        }
        log.info("订单列表加载成功!");
        return R.ok(result);
    }

    /**
     * @Description: 后台管理调用 订单管理 查询订单列表
    **/
    @Override
    public R adminGetOrderList(PageParam pageParam) {

        int offset = (pageParam.getCurrentPage()-1)*pageParam.getPageSize();
        int number = pageParam.getPageSize();

        //查询数量
        Long total = orderMapper.selectCount(null);
        //自定义查询
        List<AdminOrderVo> adminOrderVoList = orderMapper.selectAdminOrders(offset,number);

        return R.ok("订单信息查询成功！",adminOrderVoList,total);
    }

}
