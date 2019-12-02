package com.chuhui.marshal.server.serverfactory;

import com.chuhui.marshal.server.ServerContextFactory;

import java.net.InetSocketAddress;

/**
 * NioServerFactory
 * <p>
 * 基于NIO的server服务工厂
 *
 * 需要自己实现reactor模式.
 *
 * @author: 纯阳子
 * @date: 2019/12/1
 */
public class NioServerFactory extends ServerContextFactory {


    @Override
    public void configure(InetSocketAddress socketaddress, int maxConns) {

    }

    @Override
    public void startup() {

    }

    @Override
    public void start() {

    }

    @Override
    public int getLocalPort() {
        return 0;
    }
}
