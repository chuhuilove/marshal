package com.chuhui.marshal.client.resolver;

import com.chuhui.marshal.client.AbstractAnnotationResolver;
import com.chuhui.marshal.client.annotation.EnableMarshalConsumer;
import com.chuhui.marshal.client.caller.MarshalClientCaller;
import com.chuhui.marshal.client.caller.ServiceCaller;
import com.chuhui.marshal.framework.transfer.google.ConsumerRequestPackage;
import com.chuhui.marshal.framework.utils.Constant.CLIENT_REMOTE_REQUEST_FLAG;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
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


        ConsumerRequestPackage.Builder builder = ConsumerRequestPackage.newBuilder()
                .setName(name);

        for (int i = 0; i < requireProducer.length; i++) {
            builder.addRequireProducer(requireProducer[i]);
        }

        ConsumerRequestPackage build = builder.build();
        clientContextFactory.sendMessage(CLIENT_REMOTE_REQUEST_FLAG.CONSUMER_FIRST_REQUEST,build.toByteArray());
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


    @Bean
    public MarshalClientCaller serviceCaller(){
        ServiceCaller serviceCaller = new ServiceCaller(clientContextFactory);

        return serviceCaller;

    }

}
