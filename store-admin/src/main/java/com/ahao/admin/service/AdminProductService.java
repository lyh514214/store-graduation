package com.ahao.admin.service;

import com.ahao.param.AdminSaveProductParam;
import com.ahao.param.ProductSearchParam;
import com.ahao.pojo.Product;
import com.ahao.utils.R;

/**
 * @Description: 后台管理模块 --》 商品服务接口
 * @Author: ahao
 * @Date: 2023/3/6 15:29
 **/

public interface AdminProductService {

    R productListByPageOrSearch(ProductSearchParam productSearchParam);

    R saveProductByAdmin(AdminSaveProductParam adminSaveProductParam);

    R removeProductByAdmin(Integer productId);

    R updateProductByAdmin(Product product);
}
