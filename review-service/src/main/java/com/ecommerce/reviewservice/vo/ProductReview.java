package com.ecommerce.reviewservice.vo;

/**
 * Created by zhengzhiqing on 16/12/16.
 */
public class ProductReview {
    private int productId;
    private String productReview;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductReview() {
        return productReview;
    }

    public void setProductReview(String productReview) {
        this.productReview = productReview;
    }
}
