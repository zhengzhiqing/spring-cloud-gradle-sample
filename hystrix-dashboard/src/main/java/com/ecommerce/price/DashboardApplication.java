package com.ecommerce.price;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@SpringCloudApplication
public class DashboardApplication {
	public static void main(String... args) {
		new SpringApplicationBuilder(DashboardApplication.class).web(WebApplicationType.SERVLET).run(args);
	}
}
