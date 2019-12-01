package com.chuhui.marshal;

import com.chuhui.marshal.framework.utils.config.MarshalConfig;
import com.chuhui.marshal.framework.utils.config.MarshalConfig.MarshalBasicConfig;
import com.chuhui.marshal.server.ServerContextFactory;

import java.io.IOException;

/**
 * MarshalMain
 * <p>
 * //TODO description
 *
 * @author: 纯阳子
 * @date: 2019/12/1
 */
public final class MarshalMain {

    private static MarshalConfig GLOBAL_CONFIG;

    public static void run(MarshalConfig config) {
        MarshalMain marshalMain = new MarshalMain();
        marshalMain.setGlobalConfig(config);

        try {
            ServerContextFactory contextFactory = ServerContextFactory.createContextFactory();
        } catch (IOException e) {
            e.printStackTrace();
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
        StackTraceElement element = stackTrace[1];

        if (!(element.getClassName().equals(MarshalMain.class.getName()) && element.getMethodName().equals(invokeMethodName))) {
            throw new RuntimeException("MarshalMain.setGlobalConfig method must invoke by MarshalMain.run method");
        }

        GLOBAL_CONFIG = config;
    }

}
