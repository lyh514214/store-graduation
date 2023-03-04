package com.ahao.product.controller;

import com.ahao.param.ProductIdParam;
import com.ahao.param.RealProductIdsParam;
import com.ahao.pojo.Product;
import com.ahao.product.service.ProductService;
import com.ahao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 购物车服务-调用商品服务控制类
 * @Author: ahao
 * @Date: 2023/2/15 17:02
 **/

@RestController
@RequestMapping("product")
public class CartProductController {

    @Autowired
    private ProductService productService;

    /**
     * @Description: 通过商品id获取该商品信息
     * @return 商品对象
    **/
    @PostMapping("cart/detail")
    public Product cartGetProductDetail(@RequestBody ProductIdParam productIdParam, BindingResult result){
        if (result.hasErrors()){
            return null;
        }
        R r = productService.queryById(productIdParam.getProductId());
        return (Product) r.getData();
    }


    @PostMapping("cart/list")
    public List<Product> getProductByIds(@RequestBody RealProductIdsParam realProductIdsParam){
        return productService.someList(realProductIdsParam);
    }

}
