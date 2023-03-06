package com.ahao.clients;

import com.ahao.param.AdminSaveProductParam;
import com.ahao.param.ProductIdParam;
import com.ahao.param.ProductSearchParam;
import com.ahao.param.RealProductIdsParam;
import com.ahao.pojo.Product;
import com.ahao.utils.R;
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

    /**
     * @Description: 后台管理服务调用 - 商品列表的展示 满足分页和关键字搜索
    **/
    @PostMapping("product/search")
    public R adminGetProductListBySearchOrPage(@RequestBody ProductSearchParam productSearchParam);

    /**
     * @Description: 后台服务调用 - 保存商品信息
    **/
    @PostMapping("product/admin/save")
    R saveProductByAdmin(@RequestBody AdminSaveProductParam adminSaveProductParam);

    /**
     * @Description: 后台管理调用 - 删除商品信息
    **/
    @PostMapping("product/admin/remove")
    R removeProductByAdmin(@RequestBody Integer productId);

    /**
     * @Description: 后台管理调用 - 修改商品信息
    **/
    @PostMapping("product/admin/update")
    R updateProductByAdmin(@RequestBody Product product);
}
