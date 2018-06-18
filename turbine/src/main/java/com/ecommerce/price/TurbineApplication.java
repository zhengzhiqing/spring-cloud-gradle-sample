package com.ecommerce.price;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringCloudApplication
@EnableTurbine
public class TurbineApplication {
	public static void main(String... args) {
		new SpringApplicationBuilder(TurbineApplication.class).web(WebApplicationType.SERVLET).run(args);
	}
}
