package com.ahao.param;

import lombok.Data;

/**
 * @Description: 分页属性
 * @Author: ahao
 * @Date: 2023/2/3 16:32
 **/
@Data
public class PageParam {

    private int currentPage = 1;   //默认值
    private int pageSize = 15;    //默认值

}
