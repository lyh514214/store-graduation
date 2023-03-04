package com.ahao.search.service;

import com.ahao.param.ProductSearchParam;
import com.ahao.utils.R;

/**
 * @Description: 搜索服务接口
 * @Author: ahao
 * @Date: 2023/2/3 16:47
 **/
public interface SearchService {

    R searchProduct(ProductSearchParam productSearchParam);

}
