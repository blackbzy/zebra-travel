package com.itheima;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@MapperScan("com.itheima.dao")
public class Day13TravelSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(Day13TravelSpringBootApplication.class, args);
    }

}
