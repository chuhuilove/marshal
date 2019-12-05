package com.chuhui.marshal.client;

import com.chuhui.marshal.client.network.AbstractClientContextFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.lang.annotation.Annotation;
import java.text.MessageFormat;
import java.util.Objects;

import static com.chuhui.marshal.framework.utils.Constant.MAX_ANNOTATED_COUNT;

/**
 * AbstractAnnotationResolver
 *
 * @author: cyzi
 * @Date: 2019/12/4 0004
 * @Description:TODO
 */
public abstract class AbstractAnnotationResolver implements BeanFactoryPostProcessor, InitializingBean {

    final static private Logger logger = LoggerFactory.getLogger(AbstractAnnotationResolver.class);


    protected ConfigurableListableBeanFactory beanFactory;
    protected AbstractClientContextFactory clientContextFactory;

    public ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    /**
     * 用来检测beanFactory中是否存在clazz所指定的注解类型,
     * 若存在,并且该注解类型只存在一个,则返回这个注解所在的那个beanName.
     *
     * @param clazz 注解类型.
     *              会有三种注解类型调用此函数:
     *              1.{@link com.chuhui.marshal.client.annotation.EnableMarshal}
     *              2.{@link com.chuhui.marshal.client.annotation.EnableMarshalConsumer}
     *              3.{@link com.chuhui.marshal.client.annotation.EnableMarshalProducer}
     * @return 返回带有指定注解类型的那个beanName
     */
    protected final String checkAnnotation(Class clazz) {
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();




        String annotationName = clazz.getName();

        int consumerCount = 0;

        String annotatedBeanName = null;

        for (String beanName : beanDefinitionNames) {

            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);

            if (StringUtils.isNotEmpty(beanDefinition.getBeanClassName())) {

                Object bean = getBean(beanName);

                if (Objects.nonNull(bean)) {

                    Annotation annotation = bean.getClass().getAnnotation(clazz);

                    if (annotation != null) {
                        consumerCount++;

                        if (consumerCount > MAX_ANNOTATED_COUNT) {
                            BeanDefinition preAnnotatedBean = beanFactory.getBeanDefinition(annotatedBeanName);
                            String preBeanClassName = preAnnotatedBean.getBeanClassName();
                            preBeanClassName = preBeanClassName.substring(0, preBeanClassName.indexOf("$$"));


                            String beanClassName = beanDefinition.getBeanClassName();
                            beanClassName = beanClassName.substring(0, beanClassName.indexOf("$$"));


                            String format = MessageFormat.format("found multiple {0} annotation,{1} has annotated,but {2} has annotated",
                                    annotationName, preBeanClassName, beanClassName);


                            throw new IllegalArgumentException(format);
                        }
                        annotatedBeanName = beanName;
                    }
                }
            }
        }

        return annotatedBeanName;
    }

    final protected void startRemoteClient(String[] marshalServer) {
        if (logger.isDebugEnabled()) {
            logger.debug("open client");
        }
        clientContextFactory = AbstractClientContextFactory.createClientFactory(marshalServer);
        clientContextFactory.startClient();
    }

    /**
     * 使用这个函数,会影响其内部的初始化过程.....
     * @param beanName beanName
     * @return
     */
    @Deprecated
    final protected Object getBean(String beanName) {

        Object bean = null;

        try {
            bean = beanFactory.getBean(beanName);
        } catch (Exception e) {
        }

        return bean;
    }
}
