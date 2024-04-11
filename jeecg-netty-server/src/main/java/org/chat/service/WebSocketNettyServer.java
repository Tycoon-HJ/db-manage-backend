package org.chat.service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import lombok.Data;
import org.chat.handler.WebSocketNettyHandler;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * netty服务端
 * 实现DisposableBean 在容器销毁前会调用destroy 方法进行线程组的关闭
 *
 * @author Administrator
 */
@Data
@Component
public class WebSocketNettyServer implements DisposableBean {

    /**
     * 自定义入站规则
     */
    @Resource
    private WebSocketNettyHandler webSocketNettyHandler;

    /**
     * 通道初始化对象
     */
    @Resource
    private WebSocketChannelInit webSocketChannelInit;

    /**
     * boos线程组
     */
    private EventLoopGroup boos;

    /**
     * work线程组
     */
    private EventLoopGroup work;


    /**
     * 自定义启动方法
     *
     * @param port
     */
    public void start(int port) {
        // 设置boos线程组
        boos = new NioEventLoopGroup(1);
        // 设置work线程组
        EventLoopGroup work = new NioEventLoopGroup();
        // 创建启动助手
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boos, work)
                .channel(NioServerSocketChannel.class)

                .handler(new LoggingHandler())
                .childHandler(webSocketChannelInit);
//                .option(ChannelOption.SO_BACKLOG, 10240) // 服务端可连接队列大小
//                .option(ChannelOption.SO_REUSEADDR, true) // 参数表示允许重复使用本地地址和端口
//                .childOption(ChannelOption.TCP_NODELAY, true) // 是否禁用Nagle算法 简单点说是否批量发送数据 true关闭 false开启。 开启的话可以减少一定的网络开销，但影响消息实时性
//                .childOption(ChannelOption.SO_KEEPALIVE, true) // 保活开关2h没有数据服务端会发送心跳包
//                .childHandler(new ChannelInitializer<SocketChannel>() {
//                    @Override
//                    protected void initChannel(SocketChannel ch) throws Exception {
//                        ChannelPipeline pipeline = ch.pipeline();
//                        // websocket 基于http协议，所以要有http编解码器
//                        pipeline.addLast("http-codec", new HttpServerCodec());
//                        // 对写大数据流的支持
//                        pipeline.addLast("http-chunked", new ChunkedWriteHandler());
//                        // 几乎在netty中的编程，都会使用到此hanler
//                        pipeline.addLast("aggregator", new HttpObjectAggregator(65535));
        /**
         * websocket 服务器处理的协议，用于指定给客户端连接访问的路由 : /ws
         * 本handler会帮你处理一些繁重的复杂的事
         * 会帮你处理握手动作： handshaking（close, ping, pong） ping + pong = 心跳
         * 对于websocket来讲，都是以frames进行传输的，不同的数据类型对应的frames也不同
         */
        // pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
//                    }
//                });
        // 绑定ip和端口启动服务端
        ChannelFuture sync = null;
        try {
            // 绑定netty的启动端口
            sync = serverBootstrap.bind(port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
            close();
        }
        System.out.println("netty服务器启动成功" + "--端口:" + port);
        if (sync != null) {
            sync.channel().closeFuture();
        }
    }

    /**
     * 容器销毁前关闭线程组
     */
    @Override
    public void destroy() {
        close();
    }

    /**
     * 关闭方法
     */
    public void close() {
        if (boos != null) {
            boos.shutdownGracefully();
        }
        if (work != null) {
            work.shutdownGracefully();
        }
    }
}
