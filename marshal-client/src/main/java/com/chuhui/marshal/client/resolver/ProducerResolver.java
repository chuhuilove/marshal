package com.chuhui.marshal.client.resolver;

import com.chuhui.marshal.client.AbstractAnnotationResolver;
import com.chuhui.marshal.client.annotation.EnableMarshal;
import com.chuhui.marshal.client.annotation.EnableMarshalProducer;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.function.Predicate;

/**
 * ResolveEnableMarshalProducer
 * {@code EnableMarshalProducer}注解解析器
 * <p>
 * 生产者解析器
 *
 * @author: cyzi
 * @Date: 2019/12/4 0004
 * @Description:TODO
 */
@Configuration
public class ProducerResolver extends AbstractAnnotationResolver {
    final static private Logger logger = LoggerFactory.getLogger(ProducerResolver.class);


    @Override
    public void afterPropertiesSet() throws Exception {

        Map<String, Object> coreBeans = context.getBeansWithAnnotation(Controller.class);
//        startRemoteClient(marshalServer);

        // 服务所在组 group+一级RequestMapping+二级RequestMapping




        System.err.println("12345678910");
        System.err.println("12345678910");
        System.err.println("12345678910");









    }

    private String group;
    private String[] marshalServer;
    private String selfAddress;
    private String value;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        EnableMarshalProducer annotatedBeanName = getAnnotatedBeanName(applicationContext, EnableMarshalProducer.class);

        group = annotatedBeanName.group();
        marshalServer = annotatedBeanName.marshalServer();
        selfAddress = annotatedBeanName.selfAddress();
        value = annotatedBeanName.value();
        context = applicationContext;

    }
}
