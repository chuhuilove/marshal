package com.chuhui.marshal.framework.utils.utils;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.commons.lang3.ArrayUtils;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

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
     *
     * @return 返回一个{@code ServerSocketChannel}类类型
     */
    public static Class<? extends ServerSocketChannel> getNioOrEpollChannel() {
        if (Epoll.isAvailable()) {
            return EpollServerSocketChannel.class;
        } else {
            return NioServerSocketChannel.class;
        }
    }

    public static Class<? extends SocketChannel> getNioOrEpollSocketChannel() {

        if (Epoll.isAvailable()) {
            return EpollSocketChannel.class;
        }
        return NioSocketChannel.class;
    }

    public static List<SocketAddress> ipAddressToSocketAddress(String[] address) {

        if (ArrayUtils.isEmpty(address)) {
            throw new IllegalArgumentException("address is null or address length equals 0");
        }

        Function<String, SocketAddress> function = (str) -> {
            String[] split = str.split(":");
            return new InetSocketAddress(split[0], Integer.parseInt(split[1], 10));
        };

        return Arrays.stream(address).map(function).collect(Collectors.toList());

    }


}
