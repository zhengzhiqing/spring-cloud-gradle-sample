package com.ecommerce.reviewservice.controller;

import com.ecommerce.reviewservice.vo.ProductReview;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/product/review" ,method = RequestMethod.GET)
    public ProductReview getProductReview(@RequestParam int productId) {
        ServiceInstance instance = client.getLocalServiceInstance();
        ProductReview productReview = new ProductReview();
        productReview.setProductId(productId);
        productReview.setProductReview("product review");
//        try {
//            logger.info("sleep 2 seconds to test circuit breaker");
//            Thread.currentThread().sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        logger.info("productId:" + productId +
                ", productReview:" + productReview.getProductReview() + ",return from ["
                + instance.getHost() + ":"
                + instance.getPort()
                + ",serviceId:"
                + instance.getServiceId()
                + "]");
        return productReview;
    }
}