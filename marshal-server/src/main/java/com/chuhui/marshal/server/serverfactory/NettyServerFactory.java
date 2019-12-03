package com.chuhui.marshal.server.serverfactory;

import com.chuhui.marshal.framework.utils.utils.ServerFactoryUtils;
import com.chuhui.marshal.server.ServerContextFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * NettyServerFactory
 * 基于netty的服务工厂
 *
 * @author: cyzi
 * @Date: 2019/11/13 0013
 * @Description:
 */
public class NettyServerFactory extends ServerContextFactory {
    private static final Logger LOG = LoggerFactory.getLogger(NettyServerFactory.class);
    private ServerBootstrap serverBootstrap;

    private InetSocketAddress localAddress;
    private int maxClientCnnections = 60;

    private volatile boolean serverStarted;


    private ContextChannelHandler serverChannelHandler = new ContextChannelHandler();

    public NettyServerFactory() {

        EventLoopGroup bossGroup = ServerFactoryUtils.getNioOrEpollGroup(1);

        EventLoopGroup workerGroup = ServerFactoryUtils.getNioOrEpollGroup();

        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(ServerFactoryUtils.getNioOrEpollChannel())
                // parent channel options
                .option(ChannelOption.SO_REUSEADDR, true)
                // child channels options
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_LINGER, -1)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("serverFactory", serverChannelHandler);
                    }
                });
    }

    @Override
    public void configure(InetSocketAddress socketaddress, int maxConns) {
        localAddress = socketaddress;
        maxClientCnnections = maxConns;
    }

    @Override
    public void startup() {
        if (!serverStarted) {
            start();
        }

    }

    @Override
    public synchronized void start() {

        LOG.info("binding to port {}", localAddress);
        Channel parentChannel = serverBootstrap.bind(localAddress).syncUninterruptibly().channel();
        /**
         * 如果原始端口为0,则bind()之后的端口会更改,
         * 则更新localAddress以获取实际端口.
         */
        localAddress = (InetSocketAddress) parentChannel.localAddress();
        LOG.info("actual bound to port " + getLocalPort());
    }


    @Override
    public int getLocalPort() {
        return localAddress.getPort();
    }


    @ChannelHandler.Sharable
    class ContextChannelHandler extends ChannelDuplexHandler {

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            LOG.info("invoked channelActive method...");
        }

        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            LOG.info("invoked channelRegistered method...");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if(msg instanceof ByteBuf){
                ByteBuf byteBuf= (ByteBuf) msg;
                LOG.info(Thread.currentThread().getName()+" invoked channelRead method...read msg:{}", byteBuf.toString(StandardCharsets.UTF_8));
            }
            String sendMessage = "from server message:" + UUID.randomUUID().toString().replaceAll("-", "");
            Channel channel = ctx.channel();
            byte[] sendBytes = sendMessage.getBytes();
            ByteBuf byteBuf = Unpooled.directBuffer(sendBytes.length, sendBytes.length);
            byteBuf.writeBytes(sendBytes);
            channel.writeAndFlush(byteBuf);

            // 设置一个队列，

        }



        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            LOG.info("invoked channelReadComplete method...");
        }

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            LOG.info("invoked userEventTriggered method...evt:{}", evt);
        }


        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            Channel channel = ctx.channel();
            channel.close();
            LOG.error("error occurs", cause);
        }
    }


}
