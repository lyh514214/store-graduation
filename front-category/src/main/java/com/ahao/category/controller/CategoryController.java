package com.ahao.category.controller;

import com.ahao.category.service.CategoryService;
import com.ahao.param.ProductHotParam;
import com.ahao.pojo.Category;
import com.ahao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 类别控制器
 * @Author: ahao
 * @Date: 2022/12/13 1:35
 **/

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * @Description: 通过某个种类名称查询种类id
    **/
    @GetMapping("promo/{categoryName}")
    public R byName(@PathVariable("categoryName") String categoryName){
        Category category = categoryService.byName(categoryName);
        if (category!=null){
            return R.ok(category);
        }
        return R.fail("商品类别不存在");
    }

    /**
     * @Description: 通过多个种类名称查询多个id
    **/
    @GetMapping("hots")
    public R byNameList(@RequestBody ProductHotParam productHotParam){
        List<Category> categoryIds = categoryService.byNameList(productHotParam);
        if (!categoryIds.isEmpty()){
            return R.ok(categoryIds);
        }
        return R.fail("查询失败或者没有该种类！");
    }

    /**
     * @Description: 查询所有类别信息
    **/
    @GetMapping("list")
    public R list(){
        List<Category> categoryList = categoryService.list();
        if (categoryList.isEmpty()){
            return R.fail(null);
        }
        return R.ok(categoryList);
    }



}
