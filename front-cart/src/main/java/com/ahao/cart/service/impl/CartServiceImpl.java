package com.ahao.cart.service.impl;

import com.ahao.cart.mapper.CartMapper;
import com.ahao.cart.service.CartService;
import com.ahao.clients.ProductClient;
import com.ahao.param.CartParam;
import com.ahao.param.ProductIdParam;
import com.ahao.param.RealProductIdsParam;
import com.ahao.param.UserIdParam;
import com.ahao.pojo.Cart;
import com.ahao.pojo.Product;
import com.ahao.utils.R;
import com.ahao.vo.CartVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 购物车实现类
 * @Author: ahao
 * @Date: 2023/2/15 17:42
 **/

@Service
@Slf4j
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductClient productClient;

    /**
     * @Description: 保存购物车
    **/
    @Override
    public R save(CartParam cartParam) {
        // 1、判断数据库中是否存在该商品
        Integer productId = cartParam.getProductId();
        ProductIdParam productIdParam = new ProductIdParam();
        productIdParam.setProductId(productId);
        Product product = productClient.productDetail(productIdParam);
        if (product == null){
            log.info("购物车服务开启，没有该商品信息");
            return R.fail("商品已经被删除，无法添加到购物车！");
        }
        // 2、查看库存
        if (product.getProductNum() == 0){
            R fail = R.fail("已经没有库存,无法购买!");
            fail.setCode("003");
            log.info("该商品没有库存了");
            return fail;
        }
        // 3、判断购物车里有没有
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",cartParam.getUserId());
        queryWrapper.eq("product_id",cartParam.getProductId());
        Cart cart = cartMapper.selectOne(queryWrapper);
        if (cart != null){
            // 3.1  购物车里里有
            log.info("购物车里已经添加过该商品，数量+1");
            cart.setNum(cart.getNum()+1);
            cartMapper.updateById(cart);
            R ok = R.ok("商品已在购物车，数量+1");
            ok.setCode("002");
            return ok;
        }
        // 3.2  购物车没有
        // 4、封装并添加到购物车
        cart = new Cart();
        cart.setNum(1);
        cart.setUserId(cartParam.getUserId());
        cart.setProductId(cartParam.getProductId());
        cartMapper.insert(cart);
        //  5、封装数据信息流传到前端
        CartVo cartVo = new CartVo(product, cart);
        log.info("新商品添加成功，业务结束");
        return R.ok("添加购物车成功",cartVo);
    }

    /**
     * @Description: 查看购物车
    **/
    @Override
    public R list(UserIdParam userIdParam) {
        // 1、用户id查询购物车数据
        Integer userId = userIdParam.getUserId();
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<Cart> cartList = cartMapper.selectList(queryWrapper);
        // 2、判断是否存在，不存在返回一个空集合
        if (cartList.isEmpty()){
            cartList = new ArrayList<>();
            return R.ok("购物车空空如也",cartList);
        }
        // 3、获取商品id集合，调用商品服务
        ArrayList<Integer> productIds = new ArrayList<>();
        for (Cart cart : cartList) {
            productIds.add(cart.getProductId());
        }
        RealProductIdsParam realProductIdsParam = new RealProductIdsParam();
        realProductIdsParam.setProductIds(productIds);
        List<Product> productList = productClient.getProductListByIds(realProductIdsParam);
        // 4、进行vo封装
        Map<Integer, Product> map = productList.stream().collect(Collectors.toMap(Product::getProductId, v -> v));
        ArrayList<CartVo> cartVoList = new ArrayList<>();
        for (Cart cart : cartList) {
            cartVoList.add(new CartVo(map.get(cart.getProductId()),cart));
        }
        log.info("查询购物车列表结束！结果为{}条数据",cartVoList.size());
        return R.ok(cartVoList);

    }

    /**
     * @Description: 购物车修改
     * @return msg
    **/
    @Override
    public R update(CartParam cartParam) {
        // 1、查看是否还有该商品
        Integer productId = cartParam.getProductId();
        Integer userId = cartParam.getUserId();
        Integer num = cartParam.getNum();
        ProductIdParam productIdParam = new ProductIdParam();
        productIdParam.setProductId(productId);
        Product product = productClient.productDetail(productIdParam);
        if (product == null){
            log.info("修改操作失败，商品已不存在！");
            return R.fail("该商品已经不存在，修改购物车失败！");
        }
        // 2、不为空，查看库存是否满足
        if (num>product.getProductNum()){
            //库存不足
            log.info("修改操作执行成功，但是该商品的库存不足！");
            return R.fail("该商品的库存不够啦！");
        }
        // 3、数据修改
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("product_id",productId);
        Cart cart = cartMapper.selectOne(queryWrapper);
        cart.setNum(num);
        cartMapper.updateById(cart);
        log.info("修改成功成功！");
        return R.ok("购物车数量更新成功！");
    }

    /**
     * @Description: 购物车删除
     * @return com.ahao.utils.R
    **/
    @Override
    public R delete(CartParam cartParam) {
        Integer userId = cartParam.getUserId();
        Integer productId = cartParam.getProductId();
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("product_id",productId);
        int i = cartMapper.delete(queryWrapper);
        if (i == 0){
            return R.fail("不存在该购物车信息！");
        }
        return R.ok("删除购物车成功！");
    }

    /**
     * @Description: 订单服务 -mq->  清空购物车
    **/
    @Override
    public void clearIds(List<Integer> cartIds) {
        log.info("订单服务清空购物车成功");
        cartMapper.deleteBatchIds(cartIds);
    }


}
