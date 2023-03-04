package com.ahao.order;

import com.ahao.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description: 订单服务启动类
 * @Author: ahao
 * @Date: 2023/2/17 22:56
 **/

@SpringBootApplication
@MapperScan(basePackages = "com.ahao.order.mapper")
@EnableFeignClients(clients = {ProductClient.class})
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}
