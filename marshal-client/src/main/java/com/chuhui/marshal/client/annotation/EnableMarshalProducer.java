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



}
