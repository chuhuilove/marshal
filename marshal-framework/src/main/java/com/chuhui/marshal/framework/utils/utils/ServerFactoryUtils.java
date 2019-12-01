package com.chuhui.marshal.framework.utils.utils;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * ServerFactoryUtils
 * <p>
 * //TODO description
 *
 * @author: 纯阳子
 * @date: 2019/12/1
 */
public class ServerFactoryUtils {

    /**
     * 获取{@code EventLoopGroup }对象,根据所在的操作系统,来具体区分.
     * 选择原生的Nio还是使用epoll
     *
     * @param threads 线程数量
     * @return
     */
    public static EventLoopGroup getNioOrEpollGroup(int threads) {

        if (Epoll.isAvailable()) {
            return new EpollEventLoopGroup(threads);
        } else {
            return new NioEventLoopGroup(threads);
        }
    }

    public static EventLoopGroup getNioOrEpollGroup() {

        if (Epoll.isAvailable()) {
            return new EpollEventLoopGroup();
        } else {
            return new NioEventLoopGroup();
        }
    }

    /**
     * 根据epoll是否可用,来获取相对应的{@code ServerSocketChannel}
     * @return 返回一个{@code ServerSocketChannel}类类型
     */
    public static Class<? extends ServerSocketChannel> getNioOrEpollChannel() {
        if (Epoll.isAvailable()) {
            return EpollServerSocketChannel.class;
        } else {
            return NioServerSocketChannel.class;
        }
    }



}
