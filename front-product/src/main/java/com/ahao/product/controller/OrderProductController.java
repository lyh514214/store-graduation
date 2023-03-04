package com.ahao.product.controller;

import com.ahao.param.RealProductIdsParam;
import com.ahao.pojo.Product;
import com.ahao.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 订单服务调用商品服务控制类
 * @Author: ahao
 * @Date: 2023/2/21 21:46
 **/

@RequestMapping("product")
@RestController
public class OrderProductController {

    @Autowired
    private ProductService productService;

    /**
     * @Description: 通过商品id集合查询商品信息
    **/
    @PostMapping("order/list")
    public List<Product> getProductsByIds(@RequestBody RealProductIdsParam realProductIdsParam){
        return productService.someList(realProductIdsParam);
    }


}
