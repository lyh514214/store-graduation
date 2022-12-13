package com.ahao.category.service.impl;

import com.ahao.category.mapper.CategoryMapper;
import com.ahao.category.service.CategoryService;
import com.ahao.pojo.Category;
import com.ahao.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 分类名称业务层
 * @Author: ahao
 * @Date: 2022/12/13 1:45
 **/
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Category byName(String categoryName) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name",categoryName);
        Category category = categoryMapper.selectOne(queryWrapper);
        if (category != null){
            log.info("查询到分类数据{}",category);
            return category;
        }
        log.info("数据库中没有对应的数据！");
        return category;
    }
}
