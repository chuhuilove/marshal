package com.chuhui.marshal.server.servercontext;

import com.chuhui.marshal.framework.transfer.TransferObject;
import com.chuhui.marshal.framework.transfer.google.ConsumerRequestPackage;
import com.chuhui.marshal.framework.transfer.google.ProducerRequestPackage;
import com.chuhui.marshal.framework.utils.DataCheckUtils;
import com.chuhui.marshal.server.ServerContext;
import com.chuhui.marshal.server.serverfactory.NettyServerFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.chuhui.marshal.framework.utils.Constant.CLIENT_REMOTE_REQUEST_FLAG.CONSUMER_FIRST_REQUEST;
import static com.chuhui.marshal.framework.utils.Constant.CLIENT_REMOTE_REQUEST_FLAG.PRODUCER_FIRST_REQUEST;

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

            case CONSUMER_FIRST_REQUEST:
                ConsumerRequestPackage clientRequestPackage = ConsumerRequestPackage.parseFrom(transferObject.getDataBody());
                customerFirstRequest(clientRequestPackage);
                break;
            case PRODUCER_FIRST_REQUEST:
                ProducerRequestPackage requestPackage = ProducerRequestPackage.parseFrom(transferObject.getDataBody());
                producerFirstRequest(requestPackage);
            default:
                break;

        }

    }


    @Override
    protected void customerFirstRequest(ConsumerRequestPackage requestPackage) {

        String name = requestPackage.getName();

        int requireProducerCount = requestPackage.getRequireProducerCount();

        logger.error("name is {}", name);

        for (int i = 0; i < requireProducerCount; i++) {
            String requireProducer = requestPackage.getRequireProducer(i);
            List<ProducerRequestPackage> producerRequestPackages = PRODUCER_SERVICES.get(requireProducer);

            if(CollectionUtils.isEmpty(producerRequestPackages)){
                logger.error("customer need package {} not found",requireProducer);
            }else{
                logger.error("customer need package {} found,has server size:{}",requireProducer,producerRequestPackages.size());
            }
        }
    }

    @Override
    protected void producerFirstRequest(ProducerRequestPackage requestPackage) {

        List<ProducerRequestPackage> services = PRODUCER_SERVICES.get(requestPackage.getServerGroup());

        if (CollectionUtils.isEmpty(services)) {
            services = Collections.synchronizedList(new ArrayList<>());
            PRODUCER_SERVICES.put(requestPackage.getServerGroup(), services);
        }
        services.add(requestPackage);



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
