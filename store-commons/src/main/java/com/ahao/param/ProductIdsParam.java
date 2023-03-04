package com.ahao.param;

import lombok.Data;

import java.util.List;

/**
 * @Description: 按分类id和分页的商品集合查询
 * @Author: ahao
 * @Date: 2022/12/18 16:06
 **/

@Data
public class ProductIdsParam extends PageParam{

    private List<Integer> categoryID;   //类别id集合

}
