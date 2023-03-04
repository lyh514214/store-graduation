package com.ahao.param;

import lombok.Data;

/**
 * @Description: 搜索服务参数
 * @Author: ahao
 * @Date: 2023/2/3 16:08
 **/
@Data
public class ProductSearchParam extends PageParam{

    private String search;  //查询 关键字
}
