package com.dongdongshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.dongdongshop.mapper")
@EnableDiscoveryClient
public class SeckillServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(SeckillServiceApp.class, args);
    }
}
