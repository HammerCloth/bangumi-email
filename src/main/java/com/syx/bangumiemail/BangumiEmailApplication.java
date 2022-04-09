package com.syx.bangumiemail;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.syx.bangumiemail.mapper")
@EnableScheduling //开启定时任务
public class BangumiEmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(BangumiEmailApplication.class, args);

    }

}
