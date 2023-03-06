package com.ahao.product.controller;

import com.ahao.param.AdminSaveProductParam;
import com.ahao.pojo.Product;
import com.ahao.product.service.ProductService;
import com.ahao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 后台管理服务调用商品服务
 * @Author: ahao
 * @Date: 2023/3/6 16:13
 **/

@RestController
@RequestMapping("product/admin")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    /**
     * @Description: 后台管理模块 保存商品
    **/
    @PostMapping("save")
    public R saveProductByAdmin(@RequestBody AdminSaveProductParam adminSaveProductParam){
        return productService.saveProductByAdmin(adminSaveProductParam);
    }

    /**
     * @Description: 后台管理模块 删除商品信息
    **/
    @PostMapping("remove")
    public R removeProductByAdmin(@RequestBody Integer productId){
        return productService.removeProductByAdmin(productId);
    }

    /**
     * @Description: 后台管理模块 更新商品信息
    **/
    @PostMapping("update")
    public R updateProductByAdmin(@RequestBody Product product){
        return productService.updateProductByAdmin(product);
    }

}
