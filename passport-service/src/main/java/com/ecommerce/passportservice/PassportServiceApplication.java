package com.ecommerce.passportservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PassportServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(PassportServiceApplication.class).web(true).run(args);
	}

}
