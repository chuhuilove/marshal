package com.chuhui.marshal.server.serverfactory;

import com.chuhui.marshal.framework.utils.utils.ServerFactoryUtils;
import com.chuhui.marshal.server.ServerContextFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;

/**
 * NettyServerFactory
 * 基于netty的服务工厂
 *
 * @author: cyzi
 * @Date: 2019/11/13 0013
 * @Description:
 */
public class NettyServerFactory extends ServerContextFactory {

    private ServerBootstrap serverBootstrap;

    private ContextChannelHandler serverChannelHandler=new ContextChannelHandler();

    NettyServerFactory() {

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
    public void configure(String hostName, int port) {

    }


    @ChannelHandler.Sharable
    class ContextChannelHandler extends ChannelDuplexHandler {





    }


}
