package com.ahao.product.service.impl;

import com.ahao.clients.CategoryClient;
import com.ahao.clients.SearchClient;
import com.ahao.param.*;
import com.ahao.pojo.Order;
import com.ahao.pojo.Product;
import com.ahao.pojo.ProductPicture;
import com.ahao.product.mapper.PictureMapper;
import com.ahao.product.mapper.ProductMapper;
import com.ahao.product.service.ProductService;
import com.ahao.to.OrderToProduct;
import com.ahao.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: 商品业务实现类
 * @Author: ahao
 * @Date: 2022/12/13 2:16
 **/
@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private SearchClient searchClient;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

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
        return productMapper.selectBatchIds(productIds);
    }

    /**
     * @Description: 订单服务 -mq-> 商品库存减少；销量增加
    **/
    @Override
    public void subNumber(List<OrderToProduct> orderToProducts) {

        // 将集合装换为map  productId orderToProduct
        Map<Integer, OrderToProduct> map = orderToProducts.stream().collect(Collectors.toMap(OrderToProduct::getProductId, v -> v));
        //获取商品的id集合
        Set<Integer> keySet = map.keySet();
        //查询集合对应的商品信息
        List<Product> productList = productMapper.selectBatchIds(keySet);
        //修改商品信息
        for (Product product : productList) {
            Integer num = map.get(product.getProductId()).getNum();
            product.setProductNum(product.getProductNum()-num);    //库存
            product.setProductSales(product.getProductSales()+num);   //销量
        }
        //批量插入
        boolean b = updateBatchById(productList);
        if (b){
            log.info("商品库存修改成功！");
        }
        log.info("商品库存修改失败！");

    }

    /**
     * @Description: 后台管理模块  --》 保存商品
    **/
    @Transactional  //事务
    @Override
    public R saveProductByAdmin(AdminSaveProductParam adminSaveProductParam) {

        Product product = new Product();
        BeanUtils.copyProperties(adminSaveProductParam,product);

        // 先插入  商品表
        int insert = productMapper.insert(product);
        if (insert == 0){
            return R.fail("商品信息保存失败！");
        }
        //  通过查询插入的数据 获取刚插入的 商品id （数据库递增）
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("product_id").last("limit 1");
        Product theOne = productMapper.selectOne(queryWrapper);

        ProductPicture productPicture = new ProductPicture(0, theOne.getProductId(), adminSaveProductParam.getProductPicture(),
                adminSaveProductParam.getProductIntro());

        int insert1 = pictureMapper.insert(productPicture);
        if (insert1 == 0){
            return R.fail("商品图片信息保存失败！");
        }

        rabbitTemplate.convertAndSend("topic.ex","insert.product",theOne);

        return R.ok("商品信息保存成功！");
    }

    /**
     * @Description: 后台管理模块 --》  删除商品信息
    **/
    @Override
    public R removeProductByAdmin(Integer productId) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",productId);
        int delete = productMapper.delete(queryWrapper);
        if (delete == 0){
            return R.fail("商品信息删除失败！");
        }
        rabbitTemplate.convertAndSend("topic.ex","product.remove",productId);
        return R.ok("商品信息删除成功！");
    }

    /**
     * @Description: 后台管理模块 --》  修改商品信息
    **/
    @Override
    public R updateProductByAdmin(Product product) {
        int i = productMapper.updateById(product);
        if (i == 0){
            return R.fail("修改商品信息失败！");
        }
        rabbitTemplate.convertAndSend("topic.ex","insert.product",product);
        return R.ok("修改物品信息成功！");
    }


}
