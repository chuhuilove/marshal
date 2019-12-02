package com.chuhui.marshal.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


/**
 * Marshal
 * 客户端
 *
 * @author: cyzi
 * @Date: 2019/11/13 0013
 * @Description:TODO
 */
public class MarshalClient {


    public static void main(String[] args) {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new MarshalClientHandler());
                        }
                    });


            SocketAddress socketAddress = new InetSocketAddress("localhost", 1125);


            ChannelFuture future = b.connect(socketAddress).sync();

            Channel channel = future.channel();
            System.err.println("please input data:");

            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();

            ByteBuf byteBuf = Unpooled.directBuffer();
            byteBuf.writeBytes(s.getBytes());

            channel.writeAndFlush(byteBuf);

            channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }


    }

    static class MarshalClientHandler extends ChannelDuplexHandler {

        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            System.err.println("has data channelActive");

        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) {

            if (msg instanceof ByteBuf) {
                ByteBuf byteBuf = (ByteBuf) msg;
                System.err.println(byteBuf.toString(StandardCharsets.UTF_8));
            }
        }


        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) {

            String s = "闫慧,憨猪猪,我爱你.我们俩还有可能吗?"+System.nanoTime();

            ByteBuf byteBuf = Unpooled.directBuffer();
            byteBuf.writeBytes(s.getBytes());

            ctx.writeAndFlush(byteBuf);

        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            // Close the connection when an exception is raised.
            cause.printStackTrace();
            ctx.close();
        }

    }


}
