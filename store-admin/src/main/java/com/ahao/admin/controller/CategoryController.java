package com.ahao.admin.controller;

import com.ahao.admin.service.AdminCategoryService;
import com.ahao.param.PageParam;
import com.ahao.pojo.Category;
import com.ahao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 类别服务
 * @Author: ahao
 * @Date: 2023/3/6 10:19
 **/

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private AdminCategoryService adminCategoryService;

    /**
     * @Description: 分页查询 类别信息
    **/
    @GetMapping("list")
    public R categoryGetListByPage(PageParam pageParam){
        return adminCategoryService.getListByPage(pageParam);
    }

    /**
     * @Description: 类别新增
    **/
    @PostMapping("save")
    public R categorySave(Category category){
        return adminCategoryService.saveByAdmin(category);
    }

    /**
     * @Description: 类别删除
    **/
    @PostMapping("remove")
    public R categoryRemove(Integer categoryId){
        return adminCategoryService.removeByAdmin(categoryId);
    }

    /**
     * @Description: 类别更新
    **/
    @PostMapping("update")
    public R categoryUpdate(Category category){
        return adminCategoryService.updateByAdmin(category);
    }





}
