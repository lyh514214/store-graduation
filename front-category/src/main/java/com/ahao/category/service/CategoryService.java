package com.ahao.category.service;

import com.ahao.param.ProductHotParam;
import com.ahao.pojo.Category;

import java.util.List;

/**
 * @Description: 分类名称接口
 * @Author: ahao
 * @Date: 2022/12/13 1:44
 **/
public interface CategoryService {

    Category byName(String categoryName);

    List<Category> byNameList(ProductHotParam productHotParam);

    List<Category> list();
}
