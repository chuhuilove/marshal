package com.chuhui.marshal.client.resolver;

import com.chuhui.marshal.client.AbstractAnnotationResolver;
import com.chuhui.marshal.client.annotation.EnableMarshalConsumer;
import com.chuhui.marshal.framework.transfer.ClientRequestPackage;
import com.chuhui.marshal.framework.utils.Constant.REMOTE_FLAG;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import java.util.Map;


/**
 * ResolveEnableMarshalConsumer
 * {@code @EnableMarshalConsumer}注解解析器
 * <p>
 * 消费者解析器
 *
 * @author: cyzi
 * @Date: 2019/12/4 0004
 */
@Configuration
public class ConsumerResolver extends AbstractAnnotationResolver {

    final static private Logger logger = LoggerFactory.getLogger(ConsumerResolver.class);


    private String[] marshalServer;
    private String[] requireProducer;
    private String name;


    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, Object> coreBeans = context.getBeansWithAnnotation(Controller.class);
        startRemoteClient(marshalServer);


        ClientRequestPackage.Builder builder = ClientRequestPackage.newBuilder()
                .setName(name);

        for (int i = 0; i < requireProducer.length; i++) {
            builder.addRequireProducer(requireProducer[i]);
        }

        ClientRequestPackage build = builder.build();

        clientContextFactory.sendMessage(REMOTE_FLAG.CLIENT_FIRST_REQUEST,build.toByteArray());

// 客户端和服务端分离出来....

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        EnableMarshalConsumer consumer = getAnnotatedBeanName(applicationContext, EnableMarshalConsumer.class);
        //        // 获取到三个属性...
        marshalServer = consumer.marshalServer();
        requireProducer = consumer.requireProducer();
        name = consumer.name();
        context = applicationContext;
    }
}
