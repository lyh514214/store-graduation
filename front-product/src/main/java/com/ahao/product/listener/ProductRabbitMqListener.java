package com.ahao.product.listener;

import com.ahao.product.service.ProductService;
import com.ahao.to.OrderToProduct;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 商品服务mq监听
 * @Author: ahao
 * @Date: 2023/2/21 17:17
 **/
@Component
public class ProductRabbitMqListener {

    @Autowired
    private ProductService productService;

    /**
     * @Description: 订单服务 -mq-> 商品库存减少
    **/
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "sub.queue"),
                    exchange = @Exchange(value = "topic.ex"),
                    key = "sub.number"
            )
    )
    public void subNumber(List<OrderToProduct> orderToProducts){
        productService.subNumber(orderToProducts);
    }

}
