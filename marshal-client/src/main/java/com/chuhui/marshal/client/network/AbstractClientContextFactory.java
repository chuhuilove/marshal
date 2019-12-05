package com.chuhui.marshal.client.network;

import com.chuhui.marshal.framework.utils.ServerFactoryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketAddress;
import java.util.List;

/**
 * ClientFactory
 *
 * @author: cyzi
 * @Date: 2019/12/4 0004
 * @Description:TODO
 */
public abstract class AbstractClientContextFactory {
    final static private Logger logger = LoggerFactory.getLogger(AbstractClientContextFactory.class);


    List<SocketAddress> socketAddresses;

    public static AbstractClientContextFactory createClientFactory(String[] serverAddress) {

        if(logger.isDebugEnabled()){
            logger.debug("use netty as client factory,class is {}",NettyClientContextFactory.class.getName());
        }

        NettyClientContextFactory contextFactory = new NettyClientContextFactory(serverAddress);
        return contextFactory;
    }

    public abstract void startClient();

    final protected void resolveSocketAddress(String[] address) {
        socketAddresses = ServerFactoryUtils.ipAddressToSocketAddress(address);
    }

    public abstract void sendMessage(String message);

    public abstract void sendMessage(Object object);
    public abstract void sendMessage(byte[] bytes);


}
