package com.dongdongshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FileServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(FileServiceApp.class,args);
    }
}
