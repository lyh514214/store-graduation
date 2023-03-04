package com.ahao.clients;

import com.ahao.param.ProductSearchParam;
import com.ahao.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description: 搜索 服务桥
 * @Author: ahao
 * @Date: 2023/2/3 19:59
 **/
@FeignClient("search-server")
public interface SearchClient {

    @PostMapping("search/product")
    R searchProduct(@RequestBody ProductSearchParam productSearchParam);
}
