package com.ecommerce.reviewservice.controller;

import com.ecommerce.reviewservice.vo.ProductReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class ReviewController {

    private final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    @GetMapping(value = "/product/{productId}/review" ,produces = "application/json")
    public ProductReview getProductReview(@PathVariable int productId) {

        logger.info(Thread.currentThread().getName() + " start to execute...");
        long start = System.currentTimeMillis();

        ProductReview productReview = new ProductReview();
        productReview.setProductId(productId);
        productReview.setProductReview("product review");

        try {
            Thread.currentThread().sleep(new Random().nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info(Thread.currentThread().getName() + " finished in ms:" + (System.currentTimeMillis() - start)
                + ", result:" + productReview);

        return productReview;

    }
}