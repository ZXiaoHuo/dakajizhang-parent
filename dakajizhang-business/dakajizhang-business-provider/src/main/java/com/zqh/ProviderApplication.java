package com.zqh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author zhangqh
 * @date 2021/8/5 0005 17:25
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.zqh.dakajizhang.mapper")
public class ProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}
