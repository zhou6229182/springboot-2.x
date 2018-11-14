package com.springboot.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.springboot.dao")
public class YtjrServiceImplApplication {

    public static void main(String[] args) {
        SpringApplication.run(YtjrServiceImplApplication.class, args);
    }
}
