package com.ahao.admin.service.impl;

import com.ahao.admin.service.AdminCategoryService;
import com.ahao.clients.CategoryClient;
import com.ahao.param.PageParam;
import com.ahao.pojo.Category;
import com.ahao.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * @Description: 后台管理模块 -- 类别管理 实现类
 * @Author: ahao
 * @Date: 2023/3/6 10:36
 **/

@Service
@Slf4j
public class AdminCategoryServiceImpl implements AdminCategoryService {

    @Autowired
    private CategoryClient categoryClient;

    /**
     * @Description: 类别分页查询
    **/
    @Override
    @Cacheable(value = "admin.categoryList",key = "#pageParam.getCurrentPage()+'-'+#pageParam.pageSize")
    public R getListByPage(PageParam pageParam) {
        R r = categoryClient.CategoryListByPage(pageParam);
        log.info("后台管理类别查询业务结束！");
        return r;
    }

    /**
     * @Description: 类别新增
    **/
    @Override
    @Caching(
            evict = @CacheEvict(value = "admin.categoryList",allEntries = true)
    )
    //缓存
    public R saveByAdmin(Category category) {
        R r = categoryClient.saveByAdmin(category);
        log.info("类别新增业务完成！");
        return r;
    }

    /**
     * @Description: 类别删除
    **/
    @Override
    @Caching(
            evict = @CacheEvict(
                    value = "admin.categoryList",allEntries = true
            )
    )
    //缓存
    public R removeByAdmin(Integer categoryId) {
        R r = categoryClient.removeByAdmin(categoryId);
        log.info("类别删除业务完成！");
        return r;
    }

    /**
     * @Description: 类别更新
    **/
    @Override
    @Caching(
            evict = @CacheEvict(
                    value = "admin.categoryList",allEntries = true
            )
    )
    //缓存
    public R updateByAdmin(Category category) {
        R r = categoryClient.updateByAdmin(category);
        log.info("类别更新业务完成！");
        return r;
    }


}
