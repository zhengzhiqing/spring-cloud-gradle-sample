package com.ecommerce.priceservice.vo;

import java.math.BigDecimal;

/**
 * Created by zhengzhiqing on 16/12/16.
 */
public class ProductPrice {
    private int productId;
    private BigDecimal productPrice;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

}
