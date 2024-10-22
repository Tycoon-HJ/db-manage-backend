package org.hai.work;


import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 基础功能远程提供方
 *
 * @author mr.ahai
 */
@EnableDubbo
@EnableNacosConfig
@SpringBootApplication
public class BaseApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApiApplication.class, args);
    }
}
