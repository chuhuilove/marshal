package com.chuhui.marshal.client.network;

import com.chuhui.marshal.framework.utils.Constant.CLIENT_REMOTE_REQUEST_FLAG;
import com.chuhui.marshal.framework.utils.ServerFactoryUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketAddress;

import static com.chuhui.marshal.framework.utils.ServerFactoryUtils.preHandleByteBuf;

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

                    if (socketAddresses.indexOf(address) == socketAddresses.size() - 1) {
                        throw e;
                    }
                    logger.error("connect to server error:{},try next", address, e);
                }
            }
        }

    }

    @Override
    public void sendMessage(String message) {
        sendMessage(message.getBytes());
    }

    @Override
    public void sendMessage(Object object) {
        if (object instanceof String) {
            sendMessage((String) object);
        }
    }


    @Deprecated
    @Override
    public void sendMessage(byte[] bytes) {
        Channel channel = future.channel();

        ByteBuf byteBuf = preHandleByteBuf(bytes.length);
        byteBuf.writeBytes(bytes);
        channel.writeAndFlush(byteBuf);
    }

    @Override
    public void sendMessage(CLIENT_REMOTE_REQUEST_FLAG flag, byte[] bodyBytes) {
        // 传进来一个2937的byte[]数组
        // 在
        ByteBuf byteBuf = preHandleByteBuf(flag, bodyBytes.length);
        byteBuf.writeBytes(bodyBytes);
        Channel channel = future.channel();
        // 服务端只收到1024个字节...???
        // 这是为什么原因呢?
        channel.writeAndFlush(byteBuf);
    }
}
