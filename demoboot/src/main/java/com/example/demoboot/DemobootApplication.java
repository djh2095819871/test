package com.example.demoboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.demoboot.mapper")
@SpringBootApplication
public class DemobootApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemobootApplication.class, args);
    }
}
