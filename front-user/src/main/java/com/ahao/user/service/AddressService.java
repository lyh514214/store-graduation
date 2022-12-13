package com.ahao.user.service;

import com.ahao.pojo.Address;
import com.ahao.utils.R;

/**
 * @Description: 地址服务
 * @Author: ahao
 * @Date: 2022/12/5 11:38
 **/
public interface AddressService {

    /**
     * @Description: 查询有关用户的地址信息
     * @param userId 用户id
     * @return com.ahao.utils.R + 地址信息
    **/
    R list(Integer userId);


    /**
     * @Description: 保存地址信息
     * @param address 详细地址、联系人、手机号、用户ID
     * @return com.ahao.utils.R + address实体类
    **/
    R save(Address address);

    /**
     * @Description: 删除地址信息
     * @param id 地址id
     * @return com.ahao.utils.R 返回成功提示
    **/
    R remove(Integer id);
}
