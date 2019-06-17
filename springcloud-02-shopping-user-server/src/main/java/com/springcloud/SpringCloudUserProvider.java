package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SpringCloudUserProvider {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudUserProvider.class, args);

	}

}
