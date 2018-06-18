package com.ecommerce.price.controller;

import com.ecommerce.price.vo.ProductPrice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class PriceController {

    private final Logger logger = LoggerFactory.getLogger(PriceController.class);

    @GetMapping(value = "/product/{productId}/price" ,produces = "application/json")
    public ProductPrice getProductPrice(@PathVariable int productId) {

        logger.info(Thread.currentThread().getName() + " start to execute...");
        long start = System.currentTimeMillis();

        ProductPrice productPrice = new ProductPrice();
        productPrice.setProductId(productId);
        productPrice.setProductPrice(BigDecimal.TEN);

        logger.info(Thread.currentThread().getName() + " finished in ms:" + (System.currentTimeMillis() - start)
                + ", result:" + productPrice);

        return productPrice;
    }

}