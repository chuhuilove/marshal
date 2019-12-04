package com.chuhui.marshal;

import com.chuhui.marshal.framework.utils.config.MarshalConfig;
import com.chuhui.marshal.framework.utils.config.MarshalConfig.MarshalBasicConfig;
import com.chuhui.marshal.server.ServerContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Objects;

import static com.chuhui.marshal.framework.utils.utils.DataCheckUtils.assertNotNull;
import static java.util.Objects.requireNonNull;

/**
 * MarshalMain
 * <p>
 * 单机模式下的程序入口
 *
 * @author: 纯阳子
 * @date: 2019/12/1
 */
public final class MarshalStandaloneMain {
    final static private Logger logger = LoggerFactory.getLogger(MarshalStandaloneMain.class);

    private static MarshalConfig GLOBAL_CONFIG;

    public static void run(MarshalConfig config) {
        MarshalStandaloneMain marshalMain = new MarshalStandaloneMain();
        marshalMain.setGlobalConfig(config);

        try {
            ServerContextFactory contextFactory = ServerContextFactory.createContextFactory();

            Integer serverPort = requireNonNull(config.getMarshalBasic().getServerPort(), "serverPort");
            InetSocketAddress socketAddress = new InetSocketAddress(serverPort);
            Integer maxClientConns = requireNonNull(config.getMarshalBasic().getMaxClientConns(), "maxClientConns");
            contextFactory.configure(socketAddress, maxClientConns);
            contextFactory.startup();
            logger.info("marshal server started");

        } catch (IOException e) {
            logger.error("invoked {}.{} method error occurs.",MarshalStandaloneMain.class.getName(),"run",e);
        }
    }


    static public MarshalConfig getGlobalConfig() {
        if (GLOBAL_CONFIG == null) {
            throw new NullPointerException("GLOBAL_CONFIG is null");
        }
        return GLOBAL_CONFIG;
    }


    /**
     * 设置全局配置选项.这个函数只能在本类中运行.
     * 且这函数只能在{@link #run(MarshalConfig)}方法中被调用
     *
     * @param config 全局配置
     */
    private void setGlobalConfig(MarshalConfig config) {

        final String invokeMethodName = "run";
        Exception exception = new Exception();
        StackTraceElement[] stackTrace = exception.getStackTrace();
        //  获得当前堆栈中的调用序列
        // 下标为0的元素,肯定是当前函数(setGlobalConfig)
        // 下标为1的元素,肯定是调用这个函数的函数
        // 如果调用这个函数的函数不是MarshalStandaloneMain.run,则抛出异常
        StackTraceElement element = stackTrace[1];

        if (!(element.getClassName().equals(MarshalStandaloneMain.class.getName()) && element.getMethodName().equals(invokeMethodName))) {
            throw new RuntimeException("MarshalMain.setGlobalConfig method must invoke by MarshalMain.run method");
        }

        GLOBAL_CONFIG = config;
    }

}
