package com.ecommerce.gateway.service;

import com.ecommerce.gateway.vo.ProductReview;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by zhengzhiqing on 16/12/16.
 */
@Service
public class ReviewServiceProxy {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getProductReviewFallback",
                    commandProperties = {
                        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
                    })
    public ProductReview getProductReview(int productId) {
        return restTemplate.getForObject("http://REVIEW-SERVICE/product/review?productId={productId}", ProductReview.class, productId);
    }

    public ProductReview getProductReviewFallback(int productId) {
        ProductReview productReview = new ProductReview();
        productReview.setProductId(productId);
        productReview.setProductReview("fallback: empty review");
        return productReview;
    }
}