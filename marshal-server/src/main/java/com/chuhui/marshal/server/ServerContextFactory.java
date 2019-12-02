package com.chuhui.marshal.server;

import com.chuhui.marshal.MarshalStandaloneMain;
import com.chuhui.marshal.framework.utils.config.MarshalConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;

public abstract class ServerContextFactory {
    private static final Logger LOG = LoggerFactory.getLogger(ServerContextFactory.class);

    static final String MARSHAL_SERVER_CONTEXT_FACTORY = "com.chuhui.marshal.server.netty.NettyServerFactory";

    static public ServerContextFactory createContextFactory() throws IOException {
        MarshalConfig globalConfig = MarshalStandaloneMain.getGlobalConfig();
        String serverFactory = globalConfig.getServerFactory();
        if (StringUtils.isEmpty(serverFactory)) {
            serverFactory = MARSHAL_SERVER_CONTEXT_FACTORY;
        }

        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("use {} as server factory.", serverFactory);
            }
            ServerContextFactory serverCnxnFactory = (ServerContextFactory) Class.forName(serverFactory)
                    .getDeclaredConstructor().newInstance();

            return serverCnxnFactory;

        } catch (Exception e) {
            IOException ioe = new IOException("Couldn't instantiate "
                    + serverFactory);
            ioe.initCause(e);
            throw ioe;
        }
    }

    public abstract void configure(InetSocketAddress socketaddress, int maxConns);

    /**
     * 启动marshal服务
     */
    public abstract void startup();

    /**
     * 启动具体的服务,目前如启动基于Netty的服务,或基于Nio的的服务.
     */
    public abstract void start();

    public abstract int getLocalPort();


}
