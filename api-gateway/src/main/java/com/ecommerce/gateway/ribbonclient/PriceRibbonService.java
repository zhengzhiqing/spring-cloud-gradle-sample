package com.ecommerce.gateway.ribbonclient;

import com.ecommerce.gateway.vo.ProductPrice;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

/**
 * Created by zhengzhiqing on 16/12/16.
 */
@Component
public class PriceRibbonService {

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(PriceRibbonService.class);

    @HystrixCommand(fallbackMethod = "getProductPriceFallback",
                    commandProperties = {
                        @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="2000")
    })
    public ProductPrice getProductPriceWithFallback(int productId) {
        long start = System.currentTimeMillis();
        ProductPrice result = restTemplate.getForObject("http://PRICE-SERVICE/product/{productId}/price", ProductPrice.class, productId);
        logger.info("time elapsed in ms:" + (System.currentTimeMillis() - start));
        return result;
    }

    public ProductPrice getProductPriceFallback(int productId) {
        ProductPrice productPrice = new ProductPrice();
        productPrice.setProductId(productId);
        productPrice.setProductPrice(BigDecimal.ONE);
        return productPrice;
    }
}