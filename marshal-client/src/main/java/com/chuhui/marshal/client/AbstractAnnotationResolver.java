package com.chuhui.marshal.client;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public abstract class AbstractAnnotationResolver implements BeanFactoryPostProcessor {

    final static private Logger logger = LoggerFactory.getLogger(AbstractAnnotationResolver.class);

    protected ConfigurableListableBeanFactory beanFactory;


    public ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    protected final boolean checkAnnotation(Class clazz) {
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();


        String annotationName = clazz.getName();

        int consumerCount = 0;
        Annotation annotated;
        String annotatedBeanName = null;


        for (String beanName : beanDefinitionNames) {

            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);

            if (StringUtils.isNotEmpty(beanDefinition.getBeanClassName())) {
                Object bean = null;
                try {
                    bean = beanFactory.getBean(beanName);
                } catch (Exception e) {

                }

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


        return false;
    }

}
