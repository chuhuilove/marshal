package com.chuhui.marshal.framework.transfer.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ServiceDefinitionRefinement
 * 将ServiceDefinition中的数据细化,将mainPath和methodPath组合起来
 * @author: cyzi
 * @Date: 2019/12/9 0009
 * @Description:
 */
@Setter
@Getter
@ToString
public class UrlServiceDefinition {

    /**
     * 方法的全名,比如com.chuhui.orderservice.methodName
     */
    private String methodName;

    /**
     * 注解上的请求方法 比如,GET,POST或者PUT等
     */
    private String[] requestMethod;

    /**
     * 服务所在的类的全路径名
     */
    private String className;

    /**
     * service 上标注的注解
     */
    private String  serviceRequestAnnotation;

    /**
     * serviceUrl
     */
    private String serviceUrl;


}
