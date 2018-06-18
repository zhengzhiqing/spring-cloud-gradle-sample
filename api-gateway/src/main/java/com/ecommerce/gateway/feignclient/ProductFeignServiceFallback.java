package com.ecommerce.gateway.feignclient;

import com.ecommerce.gateway.vo.ProductInfo;
import org.springframework.stereotype.Component;

/**
 * Created by zhengzhiqing on 2018/6/17.
 */
@Component
public class ProductFeignServiceFallback implements ProductFeignService {

    @Override
    public ProductInfo getProductInfo(int productId) {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(productId);
        productInfo.setProductTitle("fallback: productTitle");
        productInfo.setCategoryName("fallback: categoryName");
        productInfo.setBrandName("fallback: brandName");
        return productInfo;
    }
}
