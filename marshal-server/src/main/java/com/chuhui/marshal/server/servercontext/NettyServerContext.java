package com.chuhui.marshal.server.servercontext;

import com.chuhui.marshal.framework.transfer.ClientRequestPackage;
import com.chuhui.marshal.framework.transfer.TransferObject;
import com.chuhui.marshal.framework.utils.DataCheckUtils;
import com.chuhui.marshal.server.ServerContext;
import com.chuhui.marshal.server.serverfactory.NettyServerFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * NettyServerContext
 *
 * @author: cyzi
 * @Date: 2019/12/6 0006
 * @Description:TODO
 */
public class NettyServerContext extends ServerContext {
    private static final Logger logger = LoggerFactory.getLogger(NettyServerContext.class);

    private final NettyServerFactory factory;
    private final Channel channel;

    public NettyServerContext(Channel channel, NettyServerFactory factory) {
        this.channel = channel;
        this.factory = factory;
    }


    public Channel getChannel() {
        return channel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NettyServerContext that = (NettyServerContext) o;

        return new EqualsBuilder()
                .append(factory, that.factory)
                .append(channel, that.channel)
                .isEquals();
    }


    public void processMessage(ByteBuf msg) throws Exception {
        TransferObject transferObject = DataCheckUtils.parseByteBuf(msg);

        switch (transferObject.getFlag()) {

            case CLIENT_FIRST_REQUEST:
                ClientRequestPackage clientRequestPackage = ClientRequestPackage.parseFrom(transferObject.getDataBody());
                clientFirstRequest(clientRequestPackage);
                break;
            default:
                break;

        }

    }


    @Override
    protected void clientFirstRequest(ClientRequestPackage requestPackage) {

        String name = requestPackage.getName();

        int requireProducerCount = requestPackage.getRequireProducerCount();

        logger.error("name is {}", name);

        for (int i = 0; i < requireProducerCount; i++) {
            logger.error("requireProducer[{}] is {}", i, requestPackage.getRequireProducer(i));
        }


    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(factory)
                .append(channel)
                .toHashCode();
    }

    @Override
    public void close() {

    }


}
