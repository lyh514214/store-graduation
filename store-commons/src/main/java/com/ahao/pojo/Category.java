package com.ahao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 类别
 * @Author: ahao
 * @Date: 2022/12/13 0:53
 **/

@Data
@TableName("category")
public class Category implements Serializable {

    public static final Long SerialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @JsonProperty("category_id")
    private Integer categoryId;   //分类id
    @JsonProperty("category_name")
    private String categoryName;    //分类名称

}
