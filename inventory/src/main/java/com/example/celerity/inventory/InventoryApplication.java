package com.example.celerity.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import com.example.celerity.inventory.config.RibbonConfiguration;

@EnableFeignClients("com.example.celerity.inventory.integration")
@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
@EntityScan("com.example.celerity.domain")
@ComponentScan(basePackages = "com.example.celerity")
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

}
