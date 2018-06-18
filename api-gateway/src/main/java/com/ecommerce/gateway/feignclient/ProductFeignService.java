package com.ecommerce.gateway.feignclient;

import com.ecommerce.gateway.vo.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zhengzhiqing on 2018/6/17.
 */
@FeignClient(name="product-service", fallback = ProductFeignServiceFallback.class)
public interface ProductFeignService {

    @GetMapping(value = "/product/info" ,produces = "application/json")
    ProductInfo getProductInfo(@RequestParam int productId);
}
