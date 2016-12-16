package com.ecommerce.priceservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PriceServiceApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(PriceServiceApplication.class).web(true).run(args);
	}
}
