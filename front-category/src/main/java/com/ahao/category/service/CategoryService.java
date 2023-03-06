package com.ahao.category.service;

import com.ahao.param.PageParam;
import com.ahao.param.ProductHotParam;
import com.ahao.pojo.Category;
import com.ahao.utils.R;

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

    R listByPage(PageParam pageParam);

    R saveByAdmin(Category category);

    R removeByAdmin(Integer categoryId);

    R updateByAdmin(Category category);
}
