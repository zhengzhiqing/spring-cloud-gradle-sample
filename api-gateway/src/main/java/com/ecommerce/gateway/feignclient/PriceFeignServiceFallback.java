package com.ecommerce.gateway.feignclient;

import com.ecommerce.gateway.vo.ProductPrice;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * Created by zhengzhiqing on 2018/6/17.
 */
@Component
public class PriceFeignServiceFallback implements PriceFeignService {

    @Override
    public ProductPrice getProductPrice(@RequestParam int productId) {
        ProductPrice productPrice = new ProductPrice();
        productPrice.setProductId(productId);
        productPrice.setProductPrice(BigDecimal.ONE);
        return productPrice;
    }
}
