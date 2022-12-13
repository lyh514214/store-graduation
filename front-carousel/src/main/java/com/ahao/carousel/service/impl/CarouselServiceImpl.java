package com.ahao.carousel.service.impl;

import com.ahao.carousel.mapper.CarouselMapper;
import com.ahao.carousel.service.CarouselService;
import com.ahao.pojo.Carousel;
import com.ahao.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 轮播图实现类
 * @Author: ahao
 * @Date: 2022/12/12 17:40
 **/
@Slf4j
@Service
public class CarouselServiceImpl implements CarouselService  {

    @Autowired
    private CarouselMapper carouselMapper;

    /**
     * @Description: 查询优先级最高的4个轮播图
     * @return java.lang.Object
    **/
    @Override
    public Object list() {
        QueryWrapper<Carousel> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("priority");
        IPage<Carousel> page = new Page<>();
        IPage<Carousel> selectPage = carouselMapper.selectPage(page, queryWrapper);
        List<Carousel> carouselList = selectPage.getRecords();
        log.info("轮播图查询到结果为{}","秘密ovo");
        return R.ok(carouselList);
    }
}
