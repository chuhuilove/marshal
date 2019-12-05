package com.chuhui.marshal.client.annotation;

import com.chuhui.marshal.client.resolver.ConsumerResolver;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * EnableMarshalConsumer
 * <p>
 * marshal服务消费者
 *
 * @author: cyzi
 * @Date: 2019/12/4 0004
 * @Description:TODO
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(ConsumerResolver.class)
public @interface EnableMarshalConsumer {


    /**
     * 消费者端的名字
     *
     * @return
     */
    String name();


    /**
     * 消费者需要的生产者
     *
     * @return
     */
    String[] requireProducer();

    /**
     * marshal 服务器地址.
     *
     * @return
     */
    String[] marshalServer();


}
