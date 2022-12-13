package com.ahao.carousel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: 轮播图启动类
 * @Author: ahao
 * @Date: 2022/12/11 19:39
 **/

@SpringBootApplication
@MapperScan(basePackages = "com.ahao.carousel.mapper")
public class CarouselApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarouselApplication.class,args);
    }

}
