package org.jeecg.modules.chat.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.collections4.CollectionUtils;
import org.jeecg.modules.chat.dao.Message;
import org.jeecg.modules.tbOfflineMessage.entity.TbOfflineMessage;
import org.jeecg.modules.tbOfflineMessage.service.impl.TbOfflineMessageServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 服务端自定义处理入站消息
 *
 * @author Administrator
 */
@ChannelHandler.Sharable
@Component
public class WebSocketNettyHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private final Logger logger = LoggerFactory.getLogger(WebSocketNettyHandler.class);
    /**
     * 存储用户对应的通道
     */
    Map<String, ChannelHandlerContext> MAP = new ConcurrentHashMap<>(16);
    /**
     * 存放通道和用户关联
     */
    Map<String, String> CHANNEL_USER = new ConcurrentHashMap<>(16);
    /**
     * 消息队列
     */
    Map<String, Message> messageMap = new LinkedHashMap<>();
    List<ChannelHandlerContext> channelHandlerContextList = new CopyOnWriteArrayList<>();
    @Resource
    private TbOfflineMessageServiceImpl tbOfflineMessageService;

    /**
     * 通道连接事件
     *
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        channelHandlerContextList.add(ctx);
        logger.info("有新的连接=========>>当前连接数量" + channelHandlerContextList.size());
    }

    /**
     * 通道消息事件
     *
     * @param channelHandlerContext
     * @param textWebSocketFrame
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) {
        logger.info("前端发来的消息:" + textWebSocketFrame.text());
        // 获取到需要转发的客户端
        Message message = JSON.parseObject(textWebSocketFrame.text(), Message.class);
        String receive = message.getReceive();

        // 上线通知
        if (message.getType() == 1) {
            // 处理离线消息
            List<TbOfflineMessage> tbOfflineMessages = tbOfflineMessageService.listOfflineMessageList(message.getReceive());
            if (CollectionUtils.isEmpty(tbOfflineMessages)) {
                // 无离线消息
                return;
            }
            tbOfflineMessages.forEach(e -> {
                Message sendOfflineMessage = new Message();
                sendOfflineMessage.setInfo(e.getMessageBody());
                sendOfflineMessage.setSend(e.getSender());
                sendOfflineMessage.setReceive(e.getReceiver());
                channelHandlerContext.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(sendOfflineMessage)));
            });
            // 清理离线表
            tbOfflineMessageService.deleteOfflineByReceiver(message.getReceive());
            return;
        }

        // 私聊
        if (message.getType() == 3) {
            if (!MAP.containsKey(message.getSend())) {
                MAP.put(message.getSend(), channelHandlerContext);
                CHANNEL_USER.put(channelHandlerContext.channel().id().toString(), message.getSend());
            }
            // 这个人不在线
            if (!MAP.containsKey(receive)) {
                // 先把数据写进队列缓存起来
                TbOfflineMessage tbOfflineMessage = new TbOfflineMessage();
                tbOfflineMessage.setMessageBody(message.getInfo());
                tbOfflineMessage.setSender(message.getSend());
                tbOfflineMessage.setReceiver(message.getReceive());
                tbOfflineMessage.setCreateTime(new Date());
                tbOfflineMessageService.save(tbOfflineMessage);
                return;
            }
            // 服务端转发消息到指定的客户端
            ChannelHandlerContext receiveChannelHandler = MAP.get(receive);
            receiveChannelHandler.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
        }
    }

    /**
     * 通达关闭事件
     *
     * @param ctx
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        String s = CHANNEL_USER.get(ctx.channel().id().toString());
        MAP.remove(s);
        // 给其他在线用户发送该用户离线的信息
        for (ChannelHandlerContext handlerContext : MAP.values()) {
            Message message = new Message("服务端", null, UUID.randomUUID().toString(), "用户--" + s + "--已经离线了", 2);
            handlerContext.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        String s = CHANNEL_USER.get(ctx.channel().id().toString());
        MAP.remove(s);
        // 给其他在线用户发送该用户离线的信息
        for (ChannelHandlerContext handlerContext : MAP.values()) {
            Message message = new Message("服务端", null, UUID.randomUUID().toString(), "用户--" + s + "--连接发生问题，已被迫离线了", 2);
            handlerContext.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
        }
    }
}