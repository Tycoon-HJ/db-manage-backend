package org.chat.config;

import org.chat.dao.NettyConfigDAO;
import org.chat.service.WebSocketNettyServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;

@Configuration
public class NettyConf implements CommandLineRunner {

    @Resource
    private ExecutorService executorService;
    @Resource
    private WebSocketNettyServer webSocketNettyServer;
    @Resource
    private NettyConfigDAO nettyConfigDAO;

    @Override
    public void run(String... args) throws Exception {
        executorService.execute(() -> webSocketNettyServer.start(nettyConfigDAO.getPort()));
    }
}
