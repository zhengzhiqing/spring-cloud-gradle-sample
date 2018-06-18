package com.ecommerce.gateway.feignclient;

import com.ecommerce.gateway.vo.ProductReview;
import org.springframework.stereotype.Component;

/**
 * Created by zhengzhiqing on 2018/6/17.
 */
@Component
public class ReviewFeignServiceFallback implements ReviewFeignService {
    @Override
    public ProductReview getProductReview(int productId) {
        ProductReview productReview = new ProductReview();
        productReview.setProductId(productId);
        productReview.setProductReview("fallback: empty review");
        return productReview;
    }
}
