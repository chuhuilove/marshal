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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
       // this.beanFactory = beanFactory;
//        String beanName = checkAnnotation(EnableMarshalProducer.class);
//        openClientFactory(beanName);
    }


    void openClientFactory(String beanName) {
//        Object bean = beanFactory.getBean(beanName);
//        EnableMarshalProducer producer = bean.getClass().getAnnotation(EnableMarshalProducer.class);
//
//        String group = producer.group();
//        String[] marshalServer = producer.marshalServer();
//        String selfAddress = producer.selfAddress();
//        String value = producer.value();

//        startRemoteClient(marshalServer);


        scannAllController();


//        clientContextFactory.sendMessage();


    }

    private List<String> scannAllController() {
        String[] beanDefinitionNames = null;
        //beanFactory.getBeanDefinitionNames();
        Predicate<Annotation> annotationFilter = (anno) -> anno.annotationType().equals(Controller.class) || anno.annotationType().equals(RestController.class);

        if (ArrayUtils.isNotEmpty(beanDefinitionNames)) {
            List<String> controllerBeanNames = new ArrayList<>();

            for (String beanName : beanDefinitionNames) {
                /*BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                String fullClassName = beanDefinition.getBeanClassName();

                if (StringUtils.isNotEmpty(fullClassName)) {

                    try {
                        Annotation[] declaredAnnotations = Class.forName(fullClassName).getDeclaredAnnotations();



                        if (ArrayUtils.isNotEmpty(declaredAnnotations)) {
                            logger.error("======================{}",fullClassName);

                            Arrays.stream(declaredAnnotations)
                                    .forEach(e -> logger.error(e.annotationType().getSimpleName()));
                        }

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }


                }*/


//                Object bean = getBean(beanName);
//                if (Objects.nonNull(bean)) {
//
//                    Annotation[] annotations = bean.getClass().getAnnotations();
//
//                    if (ArrayUtils.isNotEmpty(annotations)) {
//
//                        long count = Arrays.stream(annotations).filter(annotationFilter).count();
//                        if (count != 0) {
//                            logger.info("find controller name:{}", beanName);
//                        }
//                    }
//                }
            }

            return controllerBeanNames;
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {


    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
