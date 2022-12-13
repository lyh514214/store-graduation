package com.ahao.product.service.impl;

import com.ahao.clients.CategoryClient;
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
     * @Description: 返回每个总类销量最好的7个产品
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
        log.info("从front-category服务获取的数据==>{}",map);
        Integer categoryId = (Integer) map.get("categoryId");
        log.info("categoryName分类名称==>{}",map.get("categoryName"));
        IPage<Product> page = new Page<>(1, 7);
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId);
        queryWrapper.orderByDesc("product_sales");
        IPage<Product> productIPage = productMapper.selectPage(page, queryWrapper);
        List<Product> productList = productIPage.getRecords();
        log.info("ProductServiceImpl.promo业务结束，结果:{}", productList);
        return R.ok("查询数据成功", productList);
    }


}
