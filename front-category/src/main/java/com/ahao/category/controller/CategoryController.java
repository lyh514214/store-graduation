package com.ahao.category.controller;

import com.ahao.category.service.CategoryService;
import com.ahao.param.ProductPromoParam;
import com.ahao.pojo.Category;
import com.ahao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: CategoryController
 * @Author: ahao
 * @Date: 2022/12/13 1:35
 **/

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("promo/{categoryName}")
    public R byName(@PathVariable("categoryName") String categoryName){
        if (categoryName != null){
            Category category = categoryService.byName(categoryName);
            return R.ok(category);
        }
        return R.fail("商品类别不存在");
    }

}
