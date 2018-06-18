package com.ecommerce.gateway.feignclient;

import com.ecommerce.gateway.vo.ProductPrice;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zhengzhiqing on 2018/6/17.
 */
@FeignClient(name="price-service", fallback = PriceFeignServiceFallback.class)
public interface PriceFeignService {

    @GetMapping(value = "/product/price" ,produces = "application/json")
    ProductPrice getProductPrice(@RequestParam int productId);
}
