package com.chuhui.marshal.client.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * ClientHandler
 *
 *
 * @author: cyzi
 * @Date: 2019/12/4 0004
 * @Description:TODO
 */
@ChannelHandler.Sharable
class NettyConsumerHandler extends ChannelDuplexHandler {

    final static private Logger logger = LoggerFactory.getLogger(NettyConsumerHandler.class);


    @Override
    public void channelActive(ChannelHandlerContext ctx) {

        logger.info("has connect to server....");

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        if (msg instanceof ByteBuf) {
            ByteBuf byteBuf = (ByteBuf) msg;
            logger.info(byteBuf.toString(StandardCharsets.UTF_8));
        }
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

}
