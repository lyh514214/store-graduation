package com.ahao.category.controller;

import com.ahao.category.service.CategoryService;
import com.ahao.param.PageParam;
import com.ahao.pojo.Category;
import com.ahao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: AdminCategoryController
 * @Author: ahao
 * @Date: 2023/3/6 10:52
 **/

@RestController
@RequestMapping("category")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * @Description: 分页查询类别列表 后台管理调用
     **/
    @GetMapping("admin/list")
    public R listByPage(@RequestBody PageParam pageParam){
        return categoryService.listByPage(pageParam);
    }

    /**
     * @Description: 类别新增  后台管理调用
    **/
    @PostMapping("admin/save")
    public R saveByAdmin(@RequestBody Category category){
        return categoryService.saveByAdmin(category);
    }

    /**
     * @Description: 类别删除  后台管理调用
    **/
    @PostMapping("admin/remove")
    public R removeByAdmin(@RequestBody Integer categoryId){
        return categoryService.removeByAdmin(categoryId);
    }

    /**
     * @Description: 类别修改  后台管理调用
    **/
    @PostMapping("admin/update")
    public R updateByAdmin(@RequestBody Category category){
        return categoryService.updateByAdmin(category);
    }

}
