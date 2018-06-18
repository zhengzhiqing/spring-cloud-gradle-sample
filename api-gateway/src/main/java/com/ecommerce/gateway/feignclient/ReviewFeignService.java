package com.ecommerce.gateway.feignclient;

import com.ecommerce.gateway.vo.ProductReview;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by zhengzhiqing on 2018/6/17.
 */
@FeignClient(name="review-service", fallback = ReviewFeignServiceFallback.class)
public interface ReviewFeignService {

    @GetMapping(value = "/product/{productId}/review" ,produces = "application/json")
    ProductReview getProductReview(@PathVariable int productId);
}
