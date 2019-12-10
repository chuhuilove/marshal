package com.chuhui.marshal.server;

import com.chuhui.marshal.framework.transfer.google.ConsumerRequestPackage;
import com.chuhui.marshal.framework.transfer.google.ProducerRequestPackage;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ServerContext
 *
 * @author: cyzi
 * @Date: 2019/12/6 0006
 * @Description:TODO
 */
public abstract class ServerContext {

    protected final static Map<String, List<ProducerRequestPackage>> PRODUCER_SERVICES=new ConcurrentHashMap<>();

    // 待好好计算一下....这玩意怎么存储,怎么执行依赖
    // 貌似.好像还有线程问题....
    protected final static Map<String, List<ConsumerRequestPackage>> CUSTOMER_SERVICES=new ConcurrentHashMap<>();


    /**
     * channel关闭的时候,需要处理的事件
     */
    public abstract void close();

    protected abstract void  customerFirstRequest(ConsumerRequestPackage requestPackage);

    protected abstract void  producerFirstRequest(ProducerRequestPackage requestPackage);

}
