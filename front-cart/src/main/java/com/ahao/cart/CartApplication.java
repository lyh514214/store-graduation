package com.ahao.cart;

import com.ahao.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description: 购物车服务启动器
 * @Author: ahao
 * @Date: 2023/2/9 17:32
 **/

@SpringBootApplication
@MapperScan(basePackages = "com.ahao.cart.mapper")
@EnableFeignClients(clients = ProductClient.class)
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class,args);
    }
}
