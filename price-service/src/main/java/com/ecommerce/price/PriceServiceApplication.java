package com.ecommerce.price;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringCloudApplication
@EnableCircuitBreaker
public class PriceServiceApplication {
	public static void main(String... args) {
		new SpringApplicationBuilder(PriceServiceApplication.class).web(WebApplicationType.SERVLET).run(args);
	}
}
