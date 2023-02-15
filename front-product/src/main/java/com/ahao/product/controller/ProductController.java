package com.ahao.product.controller;

import com.ahao.param.ProductHotParam;
import com.ahao.param.ProductIdsParam;
import com.ahao.param.ProductPromoParam;
import com.ahao.param.RealProductIdsParam;
import com.ahao.pojo.Product;
import com.ahao.product.service.ProductService;
import com.ahao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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

    /**
     * @Description: 首页类别接口
    **/
    @PostMapping("promo")
    public R promo(@RequestBody @Validated ProductPromoParam productPromoParam, BindingResult result){
        if (result.hasErrors()){
            return R.fail("传参有误，程序无法正常执行！");
        }
        return productService.getProListByCateID(productPromoParam.getCategoryName());
    }

    /**
     * @Description: 首页热门接口
    **/
    @PostMapping("hots")
    public R hots(@RequestBody @Validated ProductHotParam productHotParam,BindingResult result){
        if (result.hasErrors()){
            return R.fail("请求参数错误");
        }
        return productService.getProListByCateIds(productHotParam);
    }

    /**
     * @Description: 类别信息
    **/
    @PostMapping("category/list")
    public R categoryList(){
        return productService.getCateList();
    }

    /**
     * @Description: 类别商品接口
    **/
    @PostMapping("bycategory")
    public R byCategory(@RequestBody @Validated ProductIdsParam productIdsParam , BindingResult result){
        if (result.hasErrors()){
            return R.fail("参数有错");
        }
        return productService.queryByCategories(productIdsParam);
    }

    /**
     * @Description: 全部商品接口
     * @return com.ahao.utils.R
    **/
    @PostMapping("all")
    public R all(@RequestBody @Validated ProductIdsParam productIdsParam , BindingResult result){
        if (result.hasErrors()){
            return R.fail("参数有错");
        }
        return productService.queryByCategories(productIdsParam);
    }

    /**
     * @Description: 商品详情接口
     * @return com.ahao.utils.R
    **/
    @PostMapping("detail")
    public R detailProduct(@RequestBody HashMap<String,Integer> param){
        Integer id = param.get("productID");
        return productService.queryById(id);
    }

    /**
     * @Description: 商品图片接口
     * @return com.ahao.utils.R
    **/
    @PostMapping("pictures")
    public R picturesProduct(@RequestBody HashMap<String,Integer> param){
        Integer id = param.get("productID");
        return productService.queryPicById(id);
    }

    @GetMapping("ids")
    public List<Product> getListByIds(@RequestBody RealProductIdsParam realProductIdsParam){
        return productService.someList(realProductIdsParam);
    }

}
