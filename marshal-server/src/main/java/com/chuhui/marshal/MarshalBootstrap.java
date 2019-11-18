package com.chuhui.marshal;

import com.chuhui.marshal.framework.utils.config.MarshalConfig;
import com.chuhui.marshal.framework.utils.config.MarshalConfig.MarshalBasicConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * MarshalBootstrap
 * <p>
 * marshal服务端的启动类
 *
 * @author: cyzi
 * @Date: 2019/11/13 0013
 * @Description:TODO
 */

public class MarshalBootstrap {

    final static  private Logger logger = LoggerFactory.getLogger(MarshalBootstrap.class);

    private static final String USAGE =
            "Usage: MarshalBootstrap configFile ";

    public static void main(String[] args) {


        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        URL resource = contextClassLoader.getResource("log4j.properties");

        if (args == null || args.length <= 0) {
            System.err.println("Please specify the profile location");
            System.err.println(USAGE);
            return;
        }

        MarshalBootstrap marshal = new MarshalBootstrap();
        marshal.initConfig(args);



    }

    private void initConfig(String[] args) {


        URL resource = getClass().getClassLoader().getResource("log4j.properties");


        logger.info("start resolve config");
        MarshalConfig init = MarshalConfig.readConfigFromFile(args[0]);

        MarshalBasicConfig marshalBasic = init.getMarshalBasic();

        if(marshalBasic==null){
            // 记录日志...
            logger.error("can't  resolved config to "+MarshalBasicConfig.class.getName());
        }

    }


}
