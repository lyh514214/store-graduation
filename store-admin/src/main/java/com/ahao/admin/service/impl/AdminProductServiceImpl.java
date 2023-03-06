package com.ahao.admin.service.impl;

import com.ahao.admin.service.AdminProductService;
import com.ahao.clients.ProductClient;
import com.ahao.param.AdminSaveProductParam;
import com.ahao.param.ProductSearchParam;
import com.ahao.pojo.Product;
import com.ahao.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * @Description: 后台管理模块  --》 商品服务 实现类
 * @Author: ahao
 * @Date: 2023/3/6 15:29
 **/

@Service
@Slf4j
public class AdminProductServiceImpl implements AdminProductService {

    @Autowired
    private ProductClient productClient;

    /**
     * @Description: 商品关键字查询和分页展示
    **/
    @Cacheable(
            value = "admin.productList",key = "#productSearchParam.currentPage+'-'+#productSearchParam.pageSize+'-'+#productSearchParam.search"
    )
    @Override
    public R productListByPageOrSearch(ProductSearchParam productSearchParam) {
        R r = productClient.adminGetProductListBySearchOrPage(productSearchParam);
        log.info("商品列表加载完成！");
        return r;
    }

    /**
     * @Description: 保存商品
    **/
    @Caching(
            evict = {
                    @CacheEvict(value = "list.product", allEntries = true),
                    @CacheEvict(value = "admin.productList",allEntries = true)
            }
    )//缓存
    @Override
    public R saveProductByAdmin(AdminSaveProductParam adminSaveProductParam) {
        return productClient.saveProductByAdmin(adminSaveProductParam);
    }

    /**
     * @Description: 删除商品
    **/
    @Caching(
            evict = {
                    @CacheEvict(value = "list.product", allEntries = true),
                    @CacheEvict(value = "admin.productList",allEntries = true)
            }
    )//缓存
    @Override
    public R removeProductByAdmin(Integer productId) {
        return productClient.removeProductByAdmin(productId);
    }

    /**
     * @Description: 修改商品信息
    **/
    @Caching(
            evict = {
                    @CacheEvict(value = "list.product", allEntries = true),
                    @CacheEvict(value = "admin.productList",allEntries = true)
            }
    )//缓存
    @Override
    public R updateProductByAdmin(Product product) {
        return productClient.updateProductByAdmin(product);
    }


}
