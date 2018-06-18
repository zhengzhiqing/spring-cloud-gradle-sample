package com.ecommerce.reviewservice;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class ReviewServiceApplication {

	public static void main(String... args) {
		new SpringApplicationBuilder(ReviewServiceApplication.class).web(WebApplicationType.SERVLET).run(args);
	}

}
