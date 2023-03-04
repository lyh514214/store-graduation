package com.ahao.collect.service.impl;

import com.ahao.clients.ProductClient;
import com.ahao.collect.mapper.CollectMapper;
import com.ahao.collect.service.CollectService;
import com.ahao.param.CollectParam;
import com.ahao.param.RealProductIdsParam;
import com.ahao.pojo.Collect;
import com.ahao.pojo.Product;
import com.ahao.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description: 收藏功能业务逻辑实现
 * @Author: ahao
 * @Date: 2023/2/8 22:42
 **/
@Service
@Slf4j
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private ProductClient productClient;

    /**
     * @Description: 添加收藏
     * @param collectParam： 用户id、商品id
     * @return com.ahao.utils.R
     * 成功：添加收藏成功
     * 失败：该商品已经添加收藏，请到我的收藏查看
    **/
    @Override
    public R save(CollectParam collectParam) {
        Integer userId = collectParam.getUserId();
        Integer productId = collectParam.getProductId();
        Collect collect = new Collect();
        collect.setUserId(userId);
        collect.setProductId(productId);
        collect.setCollectTime(System.currentTimeMillis());
        // 1、判断是否收藏  有：返回  没有：下一步
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("product_id",productId);
        Long count = collectMapper.selectCount(queryWrapper);
        if (count >0){
            return R.fail("该商品已经添加收藏，请到我的收藏查看");
        }
        // 2、收藏逻辑
        collectMapper.insert(collect);
        log.info("收藏成功");
        return R.ok("添加收藏成功");
    }

    /**
     * @Description: 查看收藏
     * @param collectParam  用户id
    **/
    @Override
    @Cacheable(value = "list.collect",key = "#collectParam.userId")
    public R list(CollectParam collectParam) {
        Integer userId = collectParam.getUserId();
        //查询商品id集合
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.select("product_id");
        List<Object> list = collectMapper.selectObjs(queryWrapper);
        if (list.size() == 0){
            log.info("收藏为空");
            return R.fail("收藏为空");
        }
        ArrayList<Integer> productIds = new ArrayList<>();
        for (Object l : list) {
            productIds.add((Integer) l);
        }
        RealProductIdsParam realProductIdsParam = new RealProductIdsParam();
        realProductIdsParam.setProductIds(productIds);
        //调用商品服务接口
        List<Product> productList = productClient.getProductListByIds(realProductIdsParam);
        log.info("收藏查询成功，结果为{}条数据",productList.size());
        return R.ok(productList);
    }

    /**
     * @Description: 删除收藏
     * @param collectParam - 用户id、商品id
    **/
    @Override
    public R remove(CollectParam collectParam) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",collectParam.getUserId());
        queryWrapper.eq("product_id",collectParam.getProductId());
        int delCount = collectMapper.delete(queryWrapper);
        log.info("删除成功！共{}条数据",delCount);
        return R.ok("收藏移除成功！");
    }
}
