package com.ahao.cart.service;

import com.ahao.param.CartParam;
import com.ahao.param.UserIdParam;
import com.ahao.utils.R;

import java.util.List;

/**
 * @Description: 购物车接口
 * @Author: ahao
 * @Date: 2023/2/15 17:41
 **/
public interface CartService {
    R save(CartParam cartParam);

    R list(UserIdParam userIdParam);

    R update(CartParam cartParam);

    R delete(CartParam cartParam);

    void clearIds(List<Integer> cartIds);
}
