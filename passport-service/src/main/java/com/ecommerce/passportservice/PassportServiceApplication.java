package com.ecommerce.passportservice;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class PassportServiceApplication {
	public static void main(String... args) {
		new SpringApplicationBuilder(PassportServiceApplication.class).web(WebApplicationType.SERVLET).run(args);
	}
}
