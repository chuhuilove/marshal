package com.chuhui.marshal.client.annotation;

import com.chuhui.marshal.client.resolver.MarshalResolver;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 集成了{@code @EnableMarshalConsumer}
 * 和 {@code EnableMarshalProducer}
 * 这两个注解功能,使用这个注解,微服务既可以作为生产者,也可以作为消费者
 *
 * @author: cyzi
 * @Date: 2019/11/13 0013
 * @Description:TODO
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(MarshalResolver.class)
public @interface EnableMarshal {

    enum ROLES {
        /**
         * 客户端角色
         */
        CLIENT,
        /**
         * 服务端角色
         */
        SERVER
    }


    /**
     * 注册的值,这个值在集群中是唯一的
     *
     * @return
     */
    String value();

    /**
     * marshal服务器的地址
     *
     * @return 如127.0.0.1:11255
     */
    String serverAddress();

    /**
     * 欲注册的角色,可以注册为客户端角色或者注册为服务端角色
     *
     * @return 返回{code CLIENT}或者{@cdoe SERVER}
     */
    ROLES roles();

    /**
     * @return
     */
    int headrTime() default 3000;

    /**
     * 集群所属组
     * 一个服务,可以有n个具体的实例
     * @return
     */
//    String group();

    /**
     * marshal服务器的地址,
     * @return 如 {127.0.0.1:1125,127.0.0.1:1126}
     */
//    String[] marshalServerAddress();


}
