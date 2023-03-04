package com.ahao.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description: 参数-用户id-实体类
 * @Author: ahao
 * @Date: 2023/2/15 23:31
 **/

@Data
public class UserIdParam implements Serializable {

    public static final Long SerialVersionUID = 1L;

    @JsonProperty("user_id")
    @NotNull
    private Integer userId;

}
