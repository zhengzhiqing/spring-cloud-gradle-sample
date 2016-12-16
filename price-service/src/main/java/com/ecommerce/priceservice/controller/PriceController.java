package com.ecommerce.priceservice.controller;

import com.ecommerce.priceservice.vo.ProductPrice;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class PriceController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/product/price" ,method = RequestMethod.GET)
    public ProductPrice getProductPrice(@RequestParam int productId) {
        ServiceInstance instance = client.getLocalServiceInstance();
        ProductPrice productPrice = new ProductPrice();
        productPrice.setProductId(productId);
        productPrice.setProductPrice(BigDecimal.TEN);

        logger.info("productId:" + productId
                + ", price:" + productPrice.getProductPrice()
                + ", return from ["
                + instance.getHost() + ":"
                + instance.getPort()
                + ",serviceId:"
                + instance.getServiceId()
                + "]");

        return productPrice;
    }

}