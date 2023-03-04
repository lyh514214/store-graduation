package com.ahao.collect;

import com.ahao.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description: 收藏服务启动器
 * @Author: ahao
 * @Date: 2023/2/8 15:40
 **/
@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = "com.ahao.collect.mapper")
@EnableFeignClients(clients = {ProductClient.class})
public class CollectApplication {
    public static void main(String[] args) {
        SpringApplication.run(CollectApplication.class,args);
    }
}
