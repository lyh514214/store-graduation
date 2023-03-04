package com.ahao.search.controller;

import com.ahao.param.ProductSearchParam;
import com.ahao.search.service.SearchService;
import com.ahao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 搜索模块控制
 * @Author: ahao
 * @Date: 2023/2/3 16:35
 **/
@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping("product")
    public R searchProduct(@RequestBody ProductSearchParam productSearchParam){

        return searchService.searchProduct(productSearchParam);
    }
}
