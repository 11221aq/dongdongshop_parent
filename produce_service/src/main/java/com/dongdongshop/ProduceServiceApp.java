package com.dongdongshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.dongdongshop.mapper")
@EnableScheduling
public class ProduceServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ProduceServiceApp.class,args);
    }
}
