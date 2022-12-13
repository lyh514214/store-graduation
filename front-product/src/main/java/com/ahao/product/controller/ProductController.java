package com.ahao.product.controller;

import com.ahao.param.ProductPromoParam;
import com.ahao.product.service.ProductService;
import com.ahao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 商品控制器
 * @Author: ahao
 * @Date: 2022/12/13 0:09
 **/
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("promo")
    public R promo(@RequestBody @Validated ProductPromoParam productPromoParam, BindingResult result){
        if (result.hasErrors()){
            return R.fail("传参有误，程序无法正常执行！");
        }
        return productService.getProListByCateID(productPromoParam.getCategoryName());
    }
}
