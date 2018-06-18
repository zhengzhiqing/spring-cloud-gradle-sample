package com.ecommerce.gateway.ribbonclient;

import com.ecommerce.gateway.vo.ProductReview;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by zhengzhiqing on 16/12/16.
 */
@Component
public class ReviewRibbonService {

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(ReviewRibbonService.class);

    /**
     * if we want to use global default value in application.yml, no need to config commandProperties at all
     * @param productId
     * @return
     */
    @HystrixCommand(fallbackMethod = "getProductReviewFallback",
                    commandProperties = {
                        @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="2000")
    })
    public ProductReview getProductReviewWithFallback(int productId) {
        long start = System.currentTimeMillis();
        ProductReview result = restTemplate.getForObject("http://REVIEW-SERVICE/product/{productId}/review", ProductReview.class, productId);
        logger.info("time elapsed in ms:" + (System.currentTimeMillis() - start));
        return result;
    }

    public ProductReview getProductReviewFallback(int productId) {
        ProductReview productReview = new ProductReview();
        productReview.setProductId(productId);
        productReview.setProductReview("fallback: empty review");
        return productReview;
    }
}