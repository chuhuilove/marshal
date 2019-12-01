package com.chuhui.marshal.server;

import com.chuhui.marshal.MarshalMain;
import com.chuhui.marshal.framework.utils.config.MarshalConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public abstract class ServerContextFactory {
    private static final Logger LOG = LoggerFactory.getLogger(ServerContextFactory.class);

     static final String MARSHAL_SERVER_CONTEXT_FACTORY = "com.chuhui.marshal.server.netty.NettyServerFactory";






    static public ServerContextFactory createContextFactory() throws IOException {
        MarshalConfig globalConfig = MarshalMain.getGlobalConfig();
        String serverFactory = globalConfig.getServerFactory();
        if (StringUtils.isEmpty(serverFactory)) {
            serverFactory = MARSHAL_SERVER_CONTEXT_FACTORY;
        }

        try {
            if(LOG.isDebugEnabled()){
                LOG.debug("use {} as server factory.",serverFactory);
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

    public abstract void configure(String hostName,int port);


}
