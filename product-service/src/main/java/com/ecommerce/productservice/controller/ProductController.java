package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.vo.ProductInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping(value = "/product/{productId}/info" ,produces = "application/json")
    public ProductInfo getProductInfo(@PathVariable int productId) {

        logger.info(Thread.currentThread().getName() + " start to execute...");
        long start = System.currentTimeMillis();

        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(productId);
        productInfo.setProductTitle("product title");
        productInfo.setBrandName("brand name");
        productInfo.setCategoryName("category name");

        logger.info(Thread.currentThread().getName() + " finished in ms:" + (System.currentTimeMillis() - start)
                + ", result:" + productInfo);

        return productInfo;
    }
}