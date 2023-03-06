package com.ahao.admin.controller;

import com.ahao.admin.service.AdminProductService;
import com.ahao.clients.ProductClient;
import com.ahao.param.AdminSaveProductParam;
import com.ahao.param.ProductSearchParam;
import com.ahao.pojo.Product;
import com.ahao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 后台管理模块 --》 商品控制器
 * @Author: ahao
 * @Date: 2023/3/6 15:24
 **/

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private AdminProductService adminProductService;

    /**
     * @Description: es 分页 展示 商品列表
    **/
    @GetMapping("list")
    public R adminGetProductList(ProductSearchParam productSearchParam){
        return adminProductService.productListByPageOrSearch(productSearchParam);
    }

    /**
     * @Description: 保存商品
    **/
    @PostMapping("save")
    public R saveProductByAdmin(AdminSaveProductParam adminSaveProductParam){
        return adminProductService.saveProductByAdmin(adminSaveProductParam);
    }

    /**
     * @Description: 删除商品
    **/
    @PostMapping("remove")
    public R removeProductByAdmin(Integer productId){
        return adminProductService.removeProductByAdmin(productId);
    }

    /**
     * @Description: 后台管理模块 --》  修改商品信息
     **/
    @PostMapping("update")
    public R updateProductByAdmin(Product product){
        return adminProductService.updateProductByAdmin(product);
    }


}
