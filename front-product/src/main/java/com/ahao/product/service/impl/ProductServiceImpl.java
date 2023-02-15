package com.ahao.product.service.impl;

import com.ahao.clients.CategoryClient;
import com.ahao.clients.SearchClient;
import com.ahao.param.ProductHotParam;
import com.ahao.param.ProductIdsParam;
import com.ahao.param.ProductSearchParam;
import com.ahao.param.RealProductIdsParam;
import com.ahao.pojo.Product;
import com.ahao.pojo.ProductPicture;
import com.ahao.product.mapper.PictureMapper;
import com.ahao.product.mapper.ProductMapper;
import com.ahao.product.service.ProductService;
import com.ahao.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description: 商品业务实现类
 * @Author: ahao
 * @Date: 2022/12/13 2:16
 **/
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private SearchClient searchClient;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private PictureMapper pictureMapper;



    /**
     * @Description: 首页类别商品接口
     * @param categoryName 类别名称
     * @return 商品信息集合
    **/
    @Override
    @Cacheable(value = "list.product",key = "#categoryName" ,cacheManager = "cacheManagerHour")
    public R getProListByCateID(String categoryName) {
        R r = categoryClient.byName(categoryName);
        if (r.getCode().equals(R.FAIL_CODE)) {
            log.info("ProductServiceImpl.promo业务结束，结果:{}", "类别查询失败!");
            return r;
        }
        //从别的服务获取的对象通过linkedHashmap返回，我们需要进一步处理
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) r.getData();
        Integer categoryId = (Integer) map.get("category_id");
        IPage<Product> page = new Page<>(1, 7);
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId);
        queryWrapper.orderByDesc("product_sales");
        IPage<Product> productIPage = productMapper.selectPage(page, queryWrapper);
        List<Product> productList = productIPage.getRecords();
        log.info("ProductServiceImpl.promo业务结束，结果:{}", "成功");
        return R.ok("查询数据成功", productList);
    }

    /**
     * @Description: 首页热门接口
    **/
    @Override
    @Cacheable(value = "list.product",key = "#productHotParam.categoryName")
    public R getProListByCateIds(ProductHotParam productHotParam) {
        R r = categoryClient.byNameList(productHotParam);
        if (r.getCode().equals(R.FAIL_CODE)){
            return r;
        }
        if (r.getData()==null){
            return r;
        }
        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<LinkedHashMap<String,Object>> map = (ArrayList<LinkedHashMap<String,Object>>) r.getData();
        for (LinkedHashMap<String,Object> linkedHashMap : map){
            ids.add((Integer) linkedHashMap.get("category_id"));
        }
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_id",ids);
        queryWrapper.orderByDesc("product_sales");
        IPage<Product> page = new Page<>(1,7);
        IPage<Product> productIPage = productMapper.selectPage(page,queryWrapper);
        List<Product> records = productIPage.getRecords();
        long total = productIPage.getTotal();
        if (!records.isEmpty()){
            log.info("hots业务完成，热门商品查询结果为:{}条",total);
            return R.ok("热门商品查询成功",records,total);
        }
        return R.fail("没有热门商品数据");

    }

    /**
     * @Description: 类别信息接口
    **/
    @Override
    @Cacheable(value = "list.category",key = "#root.methodName",cacheManager = "cacheManagerDay")
    public R getCateList() {
        log.info("clist业务结束，结果:{}","成功");
        return categoryClient.list();
    }

    /**
     * @Description: 类别商品接口
     * 通过类别的ids进行商品的分页查询
    **/
    @Override
    @Cacheable(value = "list.product",key = "#productIdsParam.categoryID+'-'+#productIdsParam.currentPage+'-'+#productIdsParam.pageSize")
    public R queryByCategories(ProductIdsParam productIdsParam) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        if (productIdsParam.getCategoryID() != null && !productIdsParam.getCategoryID().isEmpty()){
            queryWrapper.in("category_id",productIdsParam.getCategoryID());
        }
        IPage<Product> iPage = new Page<>(productIdsParam.getCurrentPage(),productIdsParam.getPageSize());
        IPage<Product> productIPage = productMapper.selectPage(iPage, queryWrapper);
        List<Product> productList = productIPage.getRecords();
        long total = productIPage.getTotal();
        log.info("queryByCategories业务结束，结果为{}条",total);
        return R.ok("查询成功！",productList,total);
    }

    /**
     * @Description: 商品详细信息接口
     * @return com.ahao.utils.R
    **/
    @Override
    @Cacheable(value = "product",key = "#productID" )
    public R queryById(Integer productID) {
        Product product = productMapper.selectById(productID);
        log.info("queryById业务结束，已查询商品信息");
        return R.ok(product);
    }

    /**
     * @Description: 商品图片接口
     * @return com.ahao.utils.R
    **/
    @Override
    @Cacheable(value = "picture",key = "#productID")
    public R queryPicById(Integer productID) {
        QueryWrapper<ProductPicture> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("product_id",productID);
        List<ProductPicture> pictureList = pictureMapper.selectList(queryWrapper);
        log.info("图片查询queryPicById业务结束，结果:{}张图片",pictureList.size());
        return R.ok(pictureList);
    }

    /**
     * @Description: 查询所有商品信息
     * @return 所有商品信息
    **/
    @Override
    public List<Product> allList() {
        List<Product> productList = productMapper.selectList(null);
        log.info("全部商品业务完成:{}条数据",productList.size());
        return productList;
    }

    /**
     * @Description: 搜索业务
     * 调用搜索服务的client接口
    **/
    @Override
    public R search(ProductSearchParam productSearchParam) {
        log.info("搜索业务结束");
        return searchClient.searchProduct(productSearchParam);
    }

    /**
     * @Description: 通过商品id集合查询商品信息
    **/
    @Override
    public List<Product> someList(RealProductIdsParam realProductIdsParam) {
        List<Integer> productIds = realProductIdsParam.getProductIds();
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("product_id",productIds);
        return productMapper.selectList(queryWrapper);
    }

}
