package com.syx.bangumiemail;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.syx.bangumiemail.mapper")
public class BangumiEmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(BangumiEmailApplication.class, args);
    }

}
