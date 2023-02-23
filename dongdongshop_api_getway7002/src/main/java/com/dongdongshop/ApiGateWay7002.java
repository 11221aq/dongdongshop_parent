package com.dongdongshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGateWay7002 {
    public static void main(String[] args) {
        SpringApplication.run(ApiGateWay7002.class, args);
    }
}
