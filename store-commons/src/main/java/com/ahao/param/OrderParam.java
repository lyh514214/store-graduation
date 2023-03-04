package com.ahao.param;

import com.ahao.vo.CartVo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 订单接收参数
 * @Author: ahao
 * @Date: 2023/2/17 23:36
 **/

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderParam implements Serializable {

    public static final Long SerialVersionUID = 1L;

    @JsonProperty("user_id")
    private Integer userId;
    private List<CartVo> products;
}
