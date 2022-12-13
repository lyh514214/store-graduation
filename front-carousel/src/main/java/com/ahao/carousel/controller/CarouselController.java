package com.ahao.carousel.controller;

import com.ahao.carousel.service.CarouselService;
import com.ahao.pojo.Carousel;
import com.ahao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description: 轮播图控制层
 * @Author: ahao
 * @Date: 2022/12/12 17:32
 **/

@RestController
@RequestMapping("carousel")
public class CarouselController {

    @Autowired
    private CarouselService carouselService;

    @PostMapping("list")
    public Object list(){

        return carouselService.list();

    }





}
