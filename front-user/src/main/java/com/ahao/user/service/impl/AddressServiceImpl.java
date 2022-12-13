package com.ahao.user.service.impl;

import com.ahao.pojo.Address;
import com.ahao.user.mapper.AddressMapper;
import com.ahao.user.service.AddressService;
import com.ahao.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: AddressServiceImpl
 * @Author: ahao
 * @Date: 2022/12/5 11:41
 **/
@Slf4j
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    /**
     * @param userId 用户id
     * @return com.ahao.utils.R + 地址信息
     * @Description: 查询有关用户的地址信息
     **/
    @Override
    public R list(Integer userId) {
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<Address> addressList = addressMapper.selectList(queryWrapper);
        if (addressList.isEmpty()){
            log.info("AddressServiceImpl.list业务结束，结果为:{}","数据库不存在该条用户地址信息");
            return R.fail("查询失败，不存在该用户的地址信息");
        }
        log.info("AddressServiceImpl.list业务结束，结果为:{}","已返回用户地址数据");
        return R.ok("查询成功",addressList);
    }

    /**
     * @param address 详细地址、联系人、手机号、用户ID
     * @return com.ahao.utils.R + address实体类
     * @Description: 保存地址信息
     **/
    @Override
    public R save(Address address) {
        int insert = addressMapper.insert(address);
        if (insert == 0){
            log.info("AddressServiceImpl.remove业务结束，结果为:{}","数据库插入数据失败");
            return R.fail("插入地址信息失败");
        }
        return list(address.getUserId());
    }


    /**
     * @param id 地址id
     * @return com.ahao.utils.R 返回成功提示
     * @Description: 删除地址信息
     **/
    @Override
    public R remove(Integer id) {
        int i = addressMapper.deleteById(id);
        if (i == 0){
            log.info("AddressServiceImpl.remove业务结束，结果为:{}","数据删除失败");
            return R.fail("删除操作失败");
        }
        log.info("AddressServiceImpl.remove业务结束，结果为:{}","数据删除成功");
        return R.ok("删除操作成功");
    }
}
