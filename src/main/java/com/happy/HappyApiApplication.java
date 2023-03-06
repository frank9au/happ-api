package com.happy;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@Slf4j
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
public class HappyApiApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(HappyApiApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
