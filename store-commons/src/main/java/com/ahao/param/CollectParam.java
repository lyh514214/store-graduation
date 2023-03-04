package com.ahao.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Description: 收藏参数类
 * @Author: ahao
 * @Date: 2023/2/8 16:20
 **/

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectParam {

    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("product_id")
    private Integer productId;

}
