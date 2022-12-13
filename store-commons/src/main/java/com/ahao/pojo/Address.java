package com.ahao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description: Address
 * @Author: ahao
 * @Date: 2022/12/5 11:01
 **/
@Data
@TableName("address")
public class Address implements Serializable {

    public static final Long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    @NotBlank
    private String linkman;
    @NotBlank
    private String phone;
    @NotBlank
    private String address;
    @NotNull
    private Integer userId;


}
