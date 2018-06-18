package com.ecommerce.gateway.controller;

import com.ecommerce.gateway.feignclient.PriceFeignService;
import com.ecommerce.gateway.feignclient.ProductFeignService;
import com.ecommerce.gateway.feignclient.ReviewFeignService;
import com.ecommerce.gateway.vo.ProductAll;
import com.ecommerce.gateway.vo.ProductInfo;
import com.ecommerce.gateway.vo.ProductPrice;
import com.ecommerce.gateway.vo.ProductReview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhengzhiqing on 16/12/15.
 */
@RestController
public class ItemDetailFeignController {

    @Autowired
    private ProductFeignService productFeignService;

    @Autowired
    private ReviewFeignService reviewFeignService;

    @Autowired
    private PriceFeignService priceFeignService;

    private Logger logger = LoggerFactory.getLogger(ItemDetailFeignController.class);

    /**
     *
     * @param productId
     * @return
     */
    @GetMapping(value = "/api/feign/item/{productId}", produces = "application/json")
    public ProductAll getItemDetail(@PathVariable int productId) {

        ProductInfo productInfo = productFeignService.getProductInfo(productId);
        ProductPrice productPrice = priceFeignService.getProductPrice(productId);
        ProductReview productReview = reviewFeignService.getProductReview(productId);

        ProductAll productAll = new ProductAll();
        BeanUtils.copyProperties(productInfo, productAll);
        productAll.setProductPrice(productPrice.getProductPrice());
        productAll.setProductReview(productReview.getProductReview());

        return productAll;
    }
}