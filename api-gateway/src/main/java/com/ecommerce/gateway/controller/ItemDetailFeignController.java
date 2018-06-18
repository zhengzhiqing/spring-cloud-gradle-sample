package com.ecommerce.gateway.controller;

import com.ecommerce.gateway.feignclient.PriceFeignService;
import com.ecommerce.gateway.feignclient.ProductFeignService;
import com.ecommerce.gateway.feignclient.ReviewFeignService;
import com.ecommerce.gateway.vo.ProductAll;
import com.ecommerce.gateway.vo.ProductReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Created by zhengzhiqing on 16/12/15.
 */
@RestController
public class ItemDetailFeignController {

    @Autowired
    private ProductFeignService productService;

    @Autowired
    private ReviewFeignService reviewService;

    @Autowired
    private PriceFeignService priceService;

    private Logger logger = LoggerFactory.getLogger(ItemDetailFeignController.class);

    /**
     *
     * @param productId
     * @return
     */
    @GetMapping(value = "/api/feign/item/{productId}", produces = "application/json")
    public ProductAll getItemDetail(@PathVariable int productId) {

//        ProductInfo productInfo = productService.getProductInfo(productId);
//
//        ProductPrice productPrice = priceService.getProductPrice(productId);

        ProductReview productReview = reviewService.getProductReview(productId);

        ProductAll productAll = new ProductAll();
        BeanUtils.copyProperties(productReview, productAll);
        productAll.setProductPrice(BigDecimal.TEN);
        productAll.setProductTitle("productTitle");
        productAll.setBrandName("brandName");
        productAll.setCategoryName("categoryName");

        return productAll;
    }
}