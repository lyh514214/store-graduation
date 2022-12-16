package com.ahao.category.service.impl;

import com.ahao.category.mapper.CategoryMapper;
import com.ahao.category.service.CategoryService;
import com.ahao.param.ProductHotParam;
import com.ahao.pojo.Category;
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

    /**
     * @Description: 通过单个商品种类名称查种类id
    **/
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

    /**
     * @Description: 通过多个种类名称查询多个id
    **/
    @Override
    public List<Category> byNameList(ProductHotParam productHotParam) {
        List<String> categoryNames = productHotParam.getCategoryName();
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_name",categoryNames);
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        if (!categories.isEmpty()){
            log.info("业务结束，获取categoryIds成功:{}",categories);
            return categories;
        }
        log.info("获取不到数据！");
        return categories;
    }

    /**
     * @Description: 查询所有类别信息
    **/
    @Override
    public List<Category> list() {
        return categoryMapper.selectList(null);
    }
}
