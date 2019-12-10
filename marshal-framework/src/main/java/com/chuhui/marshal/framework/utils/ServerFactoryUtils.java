package com.chuhui.marshal.framework.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
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
import com.chuhui.marshal.framework.utils.Constant.CLIENT_REMOTE_REQUEST_FLAG;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ServerFactoryUtils
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

    public static final String TRANSFER_HEADER = "cyzi";
    public static final byte[] TRANSFER_HEADER_BYTES = TRANSFER_HEADER.getBytes();
    public static final int HEADER_LENGTH = TRANSFER_HEADER_BYTES.length;
    public static final int INTEGER_LENGTH = 4;
    public static final int SHORT_LENGTH = 2;

    /**
     * 这里定义着传输协议
     * 前4个字节,是字符串{@code TRANSFER_HEADER}
     * 下面4个子节,是一个int型数字,表示整个包有多长,
     * 最后面n个字节,是具体的请求长度
     *
     * @param dataBodyLength
     * @return
     */
    @Deprecated
    public static ByteBuf preHandleByteBuf(int dataBodyLength) {
        ByteBuf byteBuf = Unpooled.directBuffer();
        byteBuf.writeBytes(TRANSFER_HEADER_BYTES);
        byteBuf.writeInt(INTEGER_LENGTH + HEADER_LENGTH + dataBodyLength);

        return byteBuf;
    }

    /**
     * 传输协议
     * |------------请求头占用10个字节-----------|
     * 0----------3-------------7--------------9-----------n
     *  cyzi(cyzi)  包总长度(int) 请求标志(short)   请求体
     * @param flag 请求标志
     * @param dataBodyLength 请求体的长度
     * @return 添加了请求头的预置{@code ByteBuf}对象
     */
    public static ByteBuf preHandleByteBuf(CLIENT_REMOTE_REQUEST_FLAG flag, int dataBodyLength) {
        ByteBuf byteBuf = Unpooled.directBuffer();
        byteBuf.writeBytes(TRANSFER_HEADER_BYTES);

        byteBuf.writeInt(HEADER_LENGTH+INTEGER_LENGTH +SHORT_LENGTH+  + dataBodyLength);
        byteBuf.writeShort(flag.value);
        return byteBuf;
    }





}
