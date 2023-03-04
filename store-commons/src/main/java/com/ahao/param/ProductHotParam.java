package com.ahao.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Description: 热门商品-种类名称集合实体类
 * @Author: ahao
 * @Date: 2022/12/14 0:55
 **/

@Data
public class ProductHotParam {

    @NotEmpty
    private List<String> categoryName;
}
