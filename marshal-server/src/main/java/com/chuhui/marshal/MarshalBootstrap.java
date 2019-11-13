package com.chuhui.marshal;

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

    private static final String USAGE =
            "Usage: MarshalBootstrap configfile ";



    public static void main(String[] args) {

        if(args==null || args.length<=0){
            System.err.println("Please specify the profile location");
            System.err.println(USAGE);
            return ;
        }

        MarshalBootstrap marshal = new MarshalBootstrap();


        marshal.initConfig(args);

        // 集群和单机模式
        //
    }

    private void initConfig(String[] args) {






    }


}
