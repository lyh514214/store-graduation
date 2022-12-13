package com.ahao.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description  用户服务的启动类
 * @Author ahao
 * @Date 2022/12/4 22:20
 **/

@SpringBootApplication
@MapperScan(basePackages = "com.ahao.user.mapper")
public class FrontUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontUserApplication.class,args);
    }
}
