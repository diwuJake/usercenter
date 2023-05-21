package com.qiu.usercenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qiu.usercenter.Mapper")
public class UsercenterApplication {

    public static void main(String[] args) {
        System.out.println("第二个版本");
        SpringApplication.run(UsercenterApplication.class, args);
    }

}
