package com.ahao.vo;

import com.ahao.pojo.Cart;
import com.ahao.pojo.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 购物车传参类
 * @Author: ahao
 * @Date: 2023/2/9 18:16
 **/

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartVo implements Serializable {

    public static final Long SerialVersionUID = 1L;

    private Integer id;  //购物车id
    private Integer productId;  //商品id
    private String productName;  //商品名称
    private String productImg;   //商品显示图片
    private Double price;    //商品价钱
    private Integer num;     //商品购买数量
    private Integer maxNum;    //商品限购数量
    private Boolean check = false;    //是否勾选

    public CartVo(Product product, Cart cart){
        this.id = cart.getId();
        this.productId = product.getProductId();
        this.productName = product.getProductName();
        this.productImg = product.getProductPicture();
        this.price = product.getProductPrice();
        this.num = cart.getNum();
        this.maxNum = product.getProductNum();
        this.check = false;
    }

}
