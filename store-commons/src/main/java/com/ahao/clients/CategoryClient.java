package com.ahao.clients;

import com.ahao.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description: 模块间服务调用接口
 * product调用category
 * @Author: ahao
 * @Date: 2022/12/13 1:58
 **/
@FeignClient("category-server")
public interface CategoryClient {

    @GetMapping("category/promo/{categoryName}")
    R byName(@PathVariable("categoryName") String categoryName);

}
