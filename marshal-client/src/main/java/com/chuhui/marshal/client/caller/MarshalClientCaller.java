package com.chuhui.marshal.client.caller;

import java.lang.reflect.Type;

/**
 * caller
 * <p>
 * //TODO description
 *
 * @author: 纯阳子
 * @date: 2019/12/10
 */
public interface MarshalClientCaller {

    //post 请求
    //get 请求

    <T> T get(String group,String serviceName, Object param, Type clazz);




}
