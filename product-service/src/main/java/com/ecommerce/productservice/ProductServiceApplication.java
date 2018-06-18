package com.ecommerce.productservice;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class ProductServiceApplication {

	public static void main(String... args) {
		new SpringApplicationBuilder(ProductServiceApplication.class).web(WebApplicationType.SERVLET).run(args);
	}

}
