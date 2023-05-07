package org.hai.work;

import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import com.alibaba.nacos.spring.context.annotation.discovery.EnableNacosDiscovery;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 基础功能远程提供方
 *
 * @author mr.ahai
 */
@EnableDubbo
@SpringBootApplication
@EnableNacosConfig
@EnableNacosDiscovery
public class BaseApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApiApplication.class, args);
    }
}
