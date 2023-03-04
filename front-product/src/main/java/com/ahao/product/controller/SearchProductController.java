package com.ahao.product.controller;

import com.ahao.param.ProductSearchParam;
import com.ahao.pojo.Product;
import com.ahao.product.service.ProductService;
import com.ahao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 搜索服务调用商品服务的控制
 * @Author: ahao
 * @Date: 2023/1/1 22:32
 **/

@RestController
@RequestMapping("product")
public class SearchProductController {

    @Autowired
    private ProductService productService;

    /**
     * @Description: 查询所有商品
     * @return 所有商品信息
    **/
    @GetMapping("list")
    public List<Product> allList(){
        return productService.allList();
    }

    /**
     * @Description: 搜索相关商品
     * @return com.ahao.utils.R
    **/
    @PostMapping("search")
    public R searchProduct(@RequestBody ProductSearchParam productSearchParam){
        return productService.search(productSearchParam);
    }

}
