package com.chuhui.marshal.client.resolver;

import com.chuhui.marshal.client.AbstractAnnotationResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;

/**
 * ResolveEnableMarshalProducer
 * {@code EnableMarshalProducer}注解解析器
 *
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
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
