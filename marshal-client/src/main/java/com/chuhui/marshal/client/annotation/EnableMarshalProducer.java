package com.chuhui.marshal.client.annotation;

import com.chuhui.marshal.client.resolver.ProducerResolver;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * EnableMarshalProducer
 *
 * marshal服务生产者
 *
 * @author: cyzi
 * @Date: 2019/12/4 0004
 * @Description:TODO
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(ProducerResolver.class)
public @interface EnableMarshalProducer {

    /**
     * 生产者的名字
     * @return 在整个集群中都是唯一的
     */
    String value();

    /**
     * 生产者所在的组,
     * 和项目的路径名一致,即server.Servlet.context-path的路径一致
     *
     * @return 微服务组
     */
    String group();

    /**
     * marshal 服务器地址.
     * @return
     */
    String[] marshalServer();

    /**
     * 生产者的ip:port
     * @return
     */
    String selfAddress();

    /**
     * 是否将Spring Framework所提供的controller也注册到服务端
     * @return
     */
    boolean register() default false;

}
