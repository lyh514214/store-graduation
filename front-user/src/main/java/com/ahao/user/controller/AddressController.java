package com.ahao.user.controller;

import com.ahao.param.AddressListParam;
import com.ahao.param.AddressRemoveParam;
import com.ahao.pojo.Address;
import com.ahao.user.service.AddressService;
import com.ahao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: AddressController
 * @Author: ahao
 * @Date: 2022/12/5 11:21
 **/
@RestController
@RequestMapping("/user/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * @Description: 查询有关用户的地址信息
     * @param addressListParam 用户id
     * @param result 参数校验
     * @return com.ahao.utils.R + 地址信息
    **/
    @PostMapping("list")
    public R addressList(@RequestBody @Validated AddressListParam addressListParam, BindingResult result){

        if (result.hasErrors()){
            return R.fail("输入的用户ID不能为空");
        }
        return addressService.list(addressListParam.getUserId());
    }

    /**
     * @Description: 保存地址
     * @param address 链接、电话、地址、用户id
     * @param result 参数校验
     * @return com.ahao.utils.R + 用户地址信息
    **/
    @PostMapping("save")
    public R addressSave(@RequestBody @Validated Address address, BindingResult result){
        if (result.hasErrors()){
            return R.fail("输入参数有误，请检查后重新输入");
        }
        return addressService.save(address);
    }

    @PostMapping("remove")
    public R addressRemove(@RequestBody @Validated AddressRemoveParam addressRemoveParam,BindingResult result){
        if (result.hasErrors()){
            return R.fail("输入参数有误，请检查后重新输入");
        }
        return addressService.remove(addressRemoveParam.getId());
    }

}
