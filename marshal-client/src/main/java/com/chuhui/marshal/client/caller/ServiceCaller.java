package com.chuhui.marshal.client.caller;

import com.chuhui.marshal.client.network.AbstractClientContextFactory;

import java.lang.reflect.Type;

/**
 * ServiceCaller
 *
 * @author: cyzi
 * @Date: 2019/12/11 0011
 * @Description:TODO
 */
public class ServiceCaller implements MarshalClientCaller {

//    @Autowired
//    private RestTemplate template;

    private  AbstractClientContextFactory clientContextFactory;

    public ServiceCaller( AbstractClientContextFactory clientContextFactory){
        this.clientContextFactory=clientContextFactory;
    }


    @Override
    public <T> T get(String group, String serviceName, Object param, Type clazz) {




        return null;

    }
}
