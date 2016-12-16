package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.vo.ProductInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/product/info" ,method = RequestMethod.GET)
    public ProductInfo getProductInfo(@RequestParam int productId) {
        ServiceInstance instance = client.getLocalServiceInstance();
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(productId);
        productInfo.setProductTitle("product title");
        productInfo.setBrandName("brand name");
        productInfo.setCategoryName("category name");

        logger.info("productId:" + productId
                + ", title:" + productInfo.getProductTitle()
                + ", brand:" + productInfo.getBrandName()
                + ", category:" + productInfo.getCategoryName()
                + ", return from ["
                + instance.getHost() + ":"
                + instance.getPort()
                + ",serviceId:"
                + instance.getServiceId()
                + "]");

        return productInfo;
    }
}