package com.aaronmegs.collnotice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

// 排除数据库配置
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.aaronmegs.collnotice.*"})
//@ConfigurationPropertiesScan( basePackages = {"com.aaronmegs.collnotice.config"})
public class CollNoticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollNoticeApplication.class, args);
    }

}
