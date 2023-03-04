package com.ahao.cart.controller;

import com.ahao.cart.service.CartService;
import com.ahao.param.CartParam;
import com.ahao.param.UserIdParam;
import com.ahao.pojo.Cart;
import com.ahao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @Description: 购物车控制器
 * @Author: ahao
 * @Date: 2023/2/9 17:38
 **/

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * @Description: 购物车保存
     * @return com.ahao.utils.R
    **/
    @PostMapping("save")
    public R cartSave(@RequestBody @Validated CartParam cartParam, BindingResult result){
        if (result.hasErrors()){
            return R.fail("参数有误");
        }
        return cartService.save(cartParam);
    }

    /**
     * @Description: 购物车查询
     * @return com.ahao.utils.R
    **/
    @PostMapping("list")
    public R cartList(@RequestBody @Validated UserIdParam userIdParam,BindingResult result){
        if (result.hasErrors()){
            ArrayList<Cart> cartList = new ArrayList<>();
            return R.fail("购物车信息查询失败！",cartList);
        }
        return cartService.list(userIdParam);
    }

    /**
     * @Description: 购物车修改
    **/
    @PostMapping("update")
    public R cartUpdate(@RequestBody @Validated CartParam cartParam,BindingResult result){
        if (result.hasErrors()){
            return R.fail("参数有误，修改失败！");
        }
        return cartService.update(cartParam);
    }

    /**
     * @Description: 购物车删除
     * @return com.ahao.utils.R
    **/
    @PostMapping("remove")
    public R cartRemove(@RequestBody @Validated CartParam cartParam,BindingResult result){
        if (result.hasErrors()){
            return R.fail("参数有误，删除失败");
        }
        return cartService.delete(cartParam);
    }



}
