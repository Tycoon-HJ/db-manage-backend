package org.chat.dao;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "netty")
@Component
public class NettyConfigDAO {

    @Value("${netty.address}")
    private String address;
    @Value("${netty.port}")
    private int port;
    @Value("${netty.path}")
    private String path;
}
