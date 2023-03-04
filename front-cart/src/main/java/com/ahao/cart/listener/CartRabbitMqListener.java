package com.ahao.cart.listener;

import com.ahao.cart.service.CartService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 购物车监听器
 * @Author: ahao
 * @Date: 2023/2/21 17:06
 **/

@Component
public class CartRabbitMqListener {

    @Autowired
    private CartService cartService;

    /**
     * @Description: 订单功能 -mq->  清空购物车
    **/
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(name = "clear.queue"),
                    exchange = @Exchange(value = "topic.ex"),
                    key = {"clear.cart"}
            )
    )
    public void clear(List<Integer> cartIds){
        cartService.clearIds(cartIds);
    }


}
