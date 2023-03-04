package com.ahao.clients;

import com.ahao.param.ProductIdParam;
import com.ahao.param.RealProductIdsParam;
import com.ahao.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Description: 商品 调用客户端
 * @Author: ahao
 * @Date: 2023/1/1 23:15
 **/
@FeignClient("product-server")
public interface ProductClient {

    /**
     * @Description: 获取全部商品列表接口
    **/
    @GetMapping("product/list")
    List<Product> getProductList();

    /**
     * @Description: 收藏模块调用-通过商品id集合获取这些商品信息
    **/
    @GetMapping("product/ids")
    List<Product> getProductListByIds(@RequestBody RealProductIdsParam realProductIdsParam);

    /**
     * @Description: 购物车模块调用-通过商品id获取该商品的信息
     * @return 商品信息
    **/
    @PostMapping("product/cart/detail")
    Product productDetail(@RequestBody ProductIdParam productIdParam);

    /**
     * @Description: 购物车模块调用-通过商品id集合查询商品集合
     * @return 一些商品集合
    **/
    @PostMapping("product/cart/list")
    public List<Product> getProductByIds(@RequestBody RealProductIdsParam realProductIdsParam);

    /**
     * @Description: 订单服务调用 - 通过商品id集合查询这些商品的信息
    **/
    @PostMapping("product/order/list")
    public List<Product> orderGetProducts(@RequestBody RealProductIdsParam realProductIdsParam);

}
