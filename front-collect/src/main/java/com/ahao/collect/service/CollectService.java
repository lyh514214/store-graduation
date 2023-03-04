package com.ahao.collect.service;

import com.ahao.param.CollectParam;
import com.ahao.utils.R;

/**
 * @Description: 收藏接口
 * @Author: ahao
 * @Date: 2023/2/8 22:41
 **/
public interface CollectService {

    R save(CollectParam collectParam);

    R list(CollectParam collectParam);

    R remove(CollectParam collectParam);
}
