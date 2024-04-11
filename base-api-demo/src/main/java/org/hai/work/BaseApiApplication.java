package org.hai.work;


import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 基础功能远程提供方
 *
 * @author mr.ahai
 */
@EnableDubbo
@EnableNacosConfig
@SpringBootApplication
@EnableDiscoveryClient
public class BaseApiApplication {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(BaseApiApplication.class, args);
    }
}
