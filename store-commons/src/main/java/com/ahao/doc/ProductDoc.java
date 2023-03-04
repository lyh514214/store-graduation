package com.ahao.doc;

import com.ahao.pojo.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 商品es实体类
 * @Author: ahao
 * @Date: 2023/1/7 15:58
 **/

@Data
@NoArgsConstructor
public class ProductDoc extends Product {

    /**
     * @Description: 商品标题+商品名称+商品描述
    **/
    private String all;

    public ProductDoc(Product product) {
        super(product.getProductId(),product.getProductName(),product.getCategoryId(),product.getProductTitle(), product.getProductIntro()
                ,product.getProductPicture(),product.getProductPrice(),product.getProductSellingPrice(),product.getProductNum(),product.getProductSales());

        this.all=super.getProductTitle()+super.getProductName()+super.getProductIntro();
    }
}
