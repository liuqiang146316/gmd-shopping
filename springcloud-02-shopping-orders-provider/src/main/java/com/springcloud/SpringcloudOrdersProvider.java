package com.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.springcloud.dao") //����Mabatis�ӿڵ�λ��
public class SpringcloudOrdersProvider {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringcloudOrdersProvider.class, args);
	}

}
