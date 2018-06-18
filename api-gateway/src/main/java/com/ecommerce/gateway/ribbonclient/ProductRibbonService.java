package com.ecommerce.gateway.ribbonclient;

import com.ecommerce.gateway.vo.ProductInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by zhengzhiqing on 16/12/16.
 */
@Component
public class ProductRibbonService {

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(ProductRibbonService.class);

    @HystrixCommand(fallbackMethod = "getProductInfoFallback",
                    commandProperties = {
                        @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="2000")
    })
    public ProductInfo getProductInfoWithFallback(int productId) {
        long start = System.currentTimeMillis();
        ProductInfo result = restTemplate.getForObject("http://PRODUCT-SERVICE/product/{productId}/info", ProductInfo.class, productId);
        logger.info("time elapsed in ms:" + (System.currentTimeMillis() - start));
        return result;
    }

    public ProductInfo getProductInfoFallback(int productId) {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(productId);
        productInfo.setProductTitle("fallback: productTitle");
        productInfo.setCategoryName("fallback: categoryName");
        productInfo.setBrandName("fallback: brandName");
        return productInfo;
    }
}