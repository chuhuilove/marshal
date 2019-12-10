package com.chuhui.marshal.client;

import com.chuhui.marshal.client.network.AbstractClientContextFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.annotation.Annotation;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static com.chuhui.marshal.framework.utils.Constant.MAX_ANNOTATED_COUNT;

/**
 * AbstractAnnotationResolver
 *
 * @author: cyzi
 * @Date: 2019/12/4 0004
 * @Description:TODO
 */
public abstract class AbstractAnnotationResolver implements InitializingBean, ApplicationContextAware {

    final static private Logger logger = LoggerFactory.getLogger(AbstractAnnotationResolver.class);


    protected ApplicationContext context;
    protected AbstractClientContextFactory clientContextFactory;


    final protected void startRemoteClient(String[] marshalServer) {
        if (logger.isDebugEnabled()) {
            logger.debug("open client");
        }
        clientContextFactory = AbstractClientContextFactory.createClientFactory(marshalServer);
        clientContextFactory.startClient();
    }


     protected static <T extends Annotation> T getAnnotatedBeanName(ApplicationContext context, Class<T> clazz) {

        Map<String, Object> beans = context.getBeansWithAnnotation(clazz);
        if (beans.size() != MAX_ANNOTATED_COUNT) {
            throw new IllegalArgumentException("");
        }


        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            Object value = entry.getValue();
            return value.getClass().getAnnotation(clazz);
        }
        return null;
    }


}
