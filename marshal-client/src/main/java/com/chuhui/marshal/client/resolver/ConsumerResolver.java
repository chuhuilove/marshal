package com.chuhui.marshal.client.resolver;

import com.chuhui.marshal.client.AbstractAnnotationResolver;
import com.chuhui.marshal.client.annotation.EnableMarshalConsumer;
import com.chuhui.marshal.client.network.AbstractClientContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;

/**
 * ResolveEnableMarshalConsumer
 * {@code @EnableMarshalConsumer}注解解析器
 *
 * 消费者解析器
 *
 * @author: cyzi
 * @Date: 2019/12/4 0004
 */
@Configuration
public class ConsumerResolver extends AbstractAnnotationResolver {

    final static private Logger logger = LoggerFactory.getLogger(ConsumerResolver.class);



    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;

        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();




        checkAnnotation(EnableMarshalConsumer.class);



//        connectToServer("");
    }

private AbstractClientContextFactory clientContextFactory;

    void connectToServer(String beanName) {
        Object bean = beanFactory.getBean(beanName);

        EnableMarshalConsumer consumer = bean.getClass().getAnnotation(EnableMarshalConsumer.class);

        // 获取到三个属性...
        String[] marshalServer = consumer.marshalServer();
        String[] requireProducer = consumer.requireProducer();
        String value = consumer.value();

        clientContextFactory = AbstractClientContextFactory.createClientFactory(marshalServer);
        clientContextFactory.startClient();

        clientContextFactory.sendMessage("来自客户端的问候");
    }
}
