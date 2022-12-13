package com.ahao.category;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: 类别控制器
 * @Author: ahao
 * @Date: 2022/12/13 1:06
 **/
@SpringBootApplication
@MapperScan("com.ahao.category.mapper")
public class CategoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CategoryApplication.class,args);
    }
}
