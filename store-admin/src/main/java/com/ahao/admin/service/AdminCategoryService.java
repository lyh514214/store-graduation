package com.ahao.admin.service;

import com.ahao.param.PageParam;
import com.ahao.pojo.Category;
import com.ahao.utils.R;

/**
 * @Description: 后台管理模块--类别管理接口
 * @Author: ahao
 * @Date: 2023/3/6 10:35
 **/
public interface AdminCategoryService {
    
    R getListByPage(PageParam pageParam);

    R saveByAdmin(Category category);

    R removeByAdmin(Integer categoryId);

    R updateByAdmin(Category category);
}
