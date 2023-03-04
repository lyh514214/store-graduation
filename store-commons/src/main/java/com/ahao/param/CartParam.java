package com.ahao.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description: 购物车入参实体类
 * @Author: ahao
 * @Date: 2023/2/9 18:30
 **/

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartParam {

    @JsonProperty("user_id")
    @NotNull
    private Integer userId;
    @JsonProperty("product_id")
    @NotNull
    private Integer productId;
    private Integer num;
}
