package com.ahao.product.service;

import com.ahao.param.ProductHotParam;
import com.ahao.param.ProductIdsParam;
import com.ahao.param.ProductSearchParam;
import com.ahao.param.RealProductIdsParam;
import com.ahao.pojo.Product;
import com.ahao.to.OrderToProduct;
import com.ahao.utils.R;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 商品接口
 * @Author: ahao
 * @Date: 2022/12/13 2:15
 **/
public interface ProductService extends IService<Product> {

    R getProListByCateID(String categoryName);

    R getProListByCateIds(ProductHotParam productHotParam);

    R getCateList();

    R queryByCategories(ProductIdsParam productIdsParam);

    R queryById(Integer id);

    R queryPicById(Integer id);

    //======
    List<Product> allList();

    R search(ProductSearchParam productSearchParam);

    List<Product> someList(RealProductIdsParam realProductIdsParam);

    void subNumber(List<OrderToProduct> orderToProducts);

}
