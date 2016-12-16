package com.ecommerce.gateway.controller;

import com.ecommerce.gateway.service.ReviewServiceProxy;
import com.ecommerce.gateway.vo.ProductAll;
import com.ecommerce.gateway.vo.ProductInfo;
import com.ecommerce.gateway.vo.ProductPrice;
import com.ecommerce.gateway.vo.ProductReview;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by zhengzhiqing on 16/12/15.
 */
@RestController
public class ProductController {
    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ReviewServiceProxy reviewServiceProxy;

    @RequestMapping(value = "/gw/product/detail", method = RequestMethod.GET)
    public ProductAll getProductDetail(@RequestParam int productId) {
        ProductInfo productInfo  = restTemplate.getForObject("http://PRODUCT-SERVICE/product/info?productId={productId}", ProductInfo.class, productId);
        ProductPrice productPrice = restTemplate.getForObject("http://PRICE-SERVICE/product/price?productId={productId}", ProductPrice.class, productId);
        ProductReview productReview = reviewServiceProxy.getProductReview(productId);
        ProductAll productAll = new ProductAll();
        BeanUtils.copyProperties(productInfo, productAll);
        productAll.setProductPrice(productPrice.getProductPrice());
        productAll.setProductReview(productReview.getProductReview());
        return productAll;
    }
}