package com.chuhui.marshal;

import com.chuhui.marshal.framework.config.MarshalConfig;
import com.chuhui.marshal.framework.config.MarshalConfig.MarshalBasicConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    final static private Logger logger = LoggerFactory.getLogger(MarshalBootstrap.class);

    private static final String USAGE =
            "Usage: MarshalBootstrap configFile ";

    public static void main(String[] args) {


        if (args == null || args.length <= 0) {
            System.err.println("Please specify the profile location");
            System.err.println(USAGE);
            return;
        }

        MarshalBootstrap marshal = new MarshalBootstrap();
        marshal.initConfig(args);


    }

    private void initConfig(String[] args) {

        if (logger.isDebugEnabled()) {
            logger.debug("start resolve config");
        }


       final  MarshalConfig init = MarshalConfig.readConfigFromFile(args[0]);

        MarshalBasicConfig marshalBasic = init.getMarshalBasic();

        if (marshalBasic == null) {
            // resolve config xxx.yml error occur
            // program exit
            logger.error("can't  resolved config to " + MarshalBasicConfig.class.getName());
            System.exit(1);
        }

        // 修改一下,不允许里面的东西进行修改...

        if(marshalBasic.getStandalone()){
            // 走单机模式
            MarshalStandaloneMain.run(init);
        }else{
            // 走集群模式
            // TODO 集群模式还没有设计好...
        }
    }

}
