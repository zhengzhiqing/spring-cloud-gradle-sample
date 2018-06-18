package com.ecommerce.gateway.controller;

import com.ecommerce.gateway.ribbonclient.PriceRibbonService;
import com.ecommerce.gateway.ribbonclient.ProductRibbonService;
import com.ecommerce.gateway.ribbonclient.ReviewRibbonService;
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
public class ItemDetailRibbonController {

    @Autowired
    private ReviewRibbonService reviewRibbonService;

    @Autowired
    private ProductRibbonService productRibbonService;

    @Autowired
    private PriceRibbonService priceRibbonService;

    private Logger logger = LoggerFactory.getLogger(ItemDetailRibbonController.class);

    /**
     *
     * @param productId
     * @return
     */
    @GetMapping(value = "/api/ribbon/item/{productId}", produces = "application/json")
    public ProductAll getItemDetail(@PathVariable int productId) {

        ProductInfo productInfo = productRibbonService.getProductInfoWithFallback(productId);

        ProductPrice productPrice = priceRibbonService.getProductPriceWithFallback(productId);

        ProductReview productReview = reviewRibbonService.getProductReviewWithFallback(productId);

        ProductAll productAll = new ProductAll();
        BeanUtils.copyProperties(productInfo, productAll);
        productAll.setProductPrice(productPrice.getProductPrice());
        productAll.setProductReview(productReview.getProductReview());

        return productAll;
    }
}