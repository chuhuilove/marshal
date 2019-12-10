package com.chuhui.marshal.framework.transfer.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.annotation.Annotation;

/**
 * ServiceDefinition
 * <p>
 * java中,用Class来描述类
 * Spring中,用BeanDefinition来描述bena
 * 在marshal中,用ServiceDefinition来描述一个服务
 *
 * @author: 纯阳子
 * @date: 2019/12/7
 */
@Setter
@Getter
@ToString
public class ServiceDefinition {

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
    private Annotation serviceRequestAnnotation;

    /**
     * 方法注解上的path;
     */
    private String[] methodPath;

    /**
     * 设置在class上的RequestMapping中的路径
     */
    private String[] mainPath;


    public ServiceDefinition(String className, String[] mainPath) {
        this.className = className;
        this.mainPath = mainPath;
    }

}
