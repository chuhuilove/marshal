package com.chuhui.marshal.client.network;

import com.chuhui.marshal.framework.utils.utils.ServerFactoryUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketAddress;

/**
 * NettyClientContextFactory
 * 基于netty的客户端工厂
 *
 * @author: cyzi
 * @Date: 2019/12/4 0004
 * @Description:
 */
public class NettyClientContextFactory extends AbstractClientContextFactory {

    final static private Logger logger = LoggerFactory.getLogger(NettyClientContextFactory.class);


    private NettyConsumerHandler handler = new NettyConsumerHandler();

    private Bootstrap bootstrap;

    private ChannelFuture future;

    private volatile boolean isConnected;


    NettyClientContextFactory(String[] serverAddress) {
        resolveSocketAddress(serverAddress);

        EventLoopGroup workGroup = ServerFactoryUtils.getNioOrEpollGroup(1);

        bootstrap = new Bootstrap();
        bootstrap.group(workGroup)
                .channel(ServerFactoryUtils.getNioOrEpollSocketChannel())
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("mainHandler", handler);
                    }
                });

    }


    @Override
    public void startClient() {

        if (!isConnected) {
            for (SocketAddress address : socketAddresses) {
                logger.error("start connect to server :{}", address);
                try {
                    // connect 和 bind 有区别...
                    future = bootstrap.connect(address).syncUninterruptibly();
                    isConnected = true;
                    return;
                } catch (Exception e) {
                    logger.error("connect to server error:{}", address, e);
                }
            }
        }

    }

    @Override
    public void sendMessage(String message) {
        Channel channel = future.channel();

        ByteBuf byteBuf = Unpooled.directBuffer();
        byteBuf.writeBytes(message.getBytes());
        channel.writeAndFlush(byteBuf);
    }

    @Override
    public void sendMessage(Object object) {
        if (object instanceof String) {
            sendMessage((String) object);
        }
    }
}
