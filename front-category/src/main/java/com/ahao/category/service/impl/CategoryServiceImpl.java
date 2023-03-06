package com.ahao.category.service.impl;

import com.ahao.category.mapper.CategoryMapper;
import com.ahao.category.service.CategoryService;
import com.ahao.param.PageParam;
import com.ahao.param.ProductHotParam;
import com.ahao.pojo.Category;
import com.ahao.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    /**
     * @Description: 分页查询类别信息
    **/
    @Override
    public R listByPage(PageParam pageParam) {
        int currentPage = pageParam.getCurrentPage();
        int pageSize = pageParam.getPageSize();
        IPage<Category> page = new Page<>(currentPage,pageSize);
        IPage<Category> categoryIPage = categoryMapper.selectPage(page, null);
        List<Category> categoryList = categoryIPage.getRecords();
        long total = categoryIPage.getTotal();
        return R.ok("商品类别信息查询成功！",categoryList,total);
    }

    /**
     * @Description: 类别新增
    **/
    @Override
    public R saveByAdmin(Category category) {
        int insert = categoryMapper.insert(category);
        if (insert == 0){
            return R.fail("类别保存失败！");
        }
        return R.ok("类别保存成功！");
    }

    /**
     * @Description: 类别删除
    **/
    @Override
    public R removeByAdmin(Integer categoryId) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id",categoryId);
        int delete = categoryMapper.delete(queryWrapper);
        if (delete == 0){
            return R.fail("类别删除失败！");
        }
        return R.ok("类别删除成功！");
    }

    /**
     * @Description: 类别修改
    **/
    @Override
    public R updateByAdmin(Category category) {
        int update = categoryMapper.updateById(category);
        if (update == 0){
            return R.fail("类别修改失败");
        }
        return R.fail("类别修改成功");
    }

}
