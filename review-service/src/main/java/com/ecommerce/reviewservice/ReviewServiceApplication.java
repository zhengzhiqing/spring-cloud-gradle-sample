package com.ecommerce.reviewservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ReviewServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ReviewServiceApplication.class).web(true).run(args);
	}

}
