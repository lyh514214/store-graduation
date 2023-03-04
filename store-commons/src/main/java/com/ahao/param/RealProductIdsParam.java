package com.ahao.param;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 商品id集合参数
 * @Author: ahao
 * @Date: 2023/2/9 0:35
 **/
@Data
public class RealProductIdsParam implements Serializable {

    public static final Long SerialVersionUID = 1L;

    private List<Integer> productIds;
}
