package com.ahao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 类别 ： id<->name
 * @Author: ahao
 * @Date: 2022/12/13 0:53
 **/

@Data
@TableName("category")
public class Category implements Serializable {

    public static final Long SerialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer categoryId;   //分类id
    private String categoryName;    //分类名称

}
