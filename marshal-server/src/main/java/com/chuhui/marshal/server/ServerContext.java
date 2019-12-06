package com.chuhui.marshal.server;

import com.chuhui.marshal.framework.transfer.ClientRequestPackage;

/**
 * ServerContext
 *
 * @author: cyzi
 * @Date: 2019/12/6 0006
 * @Description:TODO
 */
public abstract class ServerContext {

    /**
     * channel关闭的时候,需要处理的事件
     */
    public abstract void close();

    protected abstract void  clientFirstRequest(ClientRequestPackage requestPackage);


}
