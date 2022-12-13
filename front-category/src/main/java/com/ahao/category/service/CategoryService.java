package com.ahao.category.service;

import com.ahao.pojo.Category;
import com.ahao.utils.R;

/**
 * @Description: 分类名称接口
 * @Author: ahao
 * @Date: 2022/12/13 1:44
 **/
public interface CategoryService {

    Category byName(String categoryName);
}
