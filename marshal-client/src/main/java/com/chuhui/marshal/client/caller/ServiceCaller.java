package com.chuhui.marshal.client.caller;

import com.chuhui.marshal.client.network.AbstractClientContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

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
    public <T> T get(String serviceName, Object param, Type clazz) {

        // 去服务端查找
//        clientContextFactory.sendMessage();







        return null;
    }
}
