package com.ahao.product.service.impl;

import com.ahao.clients.CategoryClient;
import com.ahao.param.ProductHotParam;
import com.ahao.pojo.Category;
import com.ahao.pojo.Product;
import com.ahao.product.mapper.ProductMapper;
import com.ahao.product.service.ProductService;
import com.ahao.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ProductMapper productMapper;

    /**
     * @Description: 首页类别接口
     * @param categoryName 类别名称
     * @return 商品信息集合
    **/
    @Override
    public R getProListByCateID(String categoryName) {
        R r = categoryClient.byName(categoryName);
        if (r.getCode().equals(R.FAIL_CODE)) {
            log.info("ProductServiceImpl.promo业务结束，结果:{}", "类别查询失败!");
            return r;
        }
        //从别的服务获取的对象通过linkedHashmap返回，我们需要进一步处理
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) r.getData();
        Integer categoryId = (Integer) map.get("categoryId");
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
            ids.add((Integer) linkedHashMap.get("categoryId"));
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
    public R getCateList() {
        log.info("clist业务结束，结果:{}","成功");
        return categoryClient.list();
    }


}
