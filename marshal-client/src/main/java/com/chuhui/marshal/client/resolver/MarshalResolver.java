package com.chuhui.marshal.client.resolver;

import com.chuhui.marshal.client.AbstractAnnotationResolver;
import com.chuhui.marshal.client.annotation.EnableMarshal;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * ResolveMarshal
 * <p>
 * 生产者/消费者解析器
 *
 * @author: 纯阳子
 * @date: 2019/12/3
 */
@Configuration
public class MarshalResolver extends AbstractAnnotationResolver {

    final static private Logger logger = LoggerFactory.getLogger(MarshalResolver.class);
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }


    public void postProcessBeanFactory1(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;


        String s = checkAnnotation(EnableMarshal.class);


        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();

        if (ArrayUtils.isNotEmpty(beanDefinitionNames)) {

            List<String> controllerBeanNames = new ArrayList<>();

            String marshalAnnotationBeanName = null;

            for (String beanName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                String fullClassName = beanDefinition.getBeanClassName();
                if (StringUtils.isNotEmpty(fullClassName)) {

                    try {
                        Annotation[] annotations = Class.forName(fullClassName).getAnnotations();

                        if (null != annotations && annotations.length > 0) {

                            // 随便选择一个controller,向里面写一个东西
                            long count = Arrays.stream(annotations).filter(e -> e.annotationType().equals(Controller.class) || e.annotationType().equals(RestController.class)).count();
                            if (count != 0) {
                                controllerBeanNames.add(beanName);
                            }
                            // 判断类是否拥有@EnableMarshal注解
                            count = Arrays.stream(annotations).filter(e -> e.annotationType().equals(EnableMarshal.class)).count();

                            // 如果有@EnableMarshal注解
                            if (count != 0) {
                                //todo 这里还需要加一个判断,如果在微服务实例中,出现了两个EnableMarshal注解,则需要报错...
                                //todo 不允许出现两个及以上EnableMarshal注解 2019年12月3日22:54:11
                                marshalAnnotationBeanName = beanName;

                            }
                        }

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }

            }
            getRequestMappingAndRegister(marshalAnnotationBeanName, controllerBeanNames);
        }

    }

    private void getRequestMappingAndRegister(String enableMarshalBeanName, List<String> controllerBeanNames) {

        Object bean = beanFactory.getBean(enableMarshalBeanName);
        EnableMarshal annotation = bean.getClass().getAnnotation(EnableMarshal.class);


        if (CollectionUtils.isNotEmpty(controllerBeanNames)) {


            for (String controllerBeanName : controllerBeanNames) {

                Object controllerBean = beanFactory.getBean(controllerBeanName);



                // 获取到了一个controller实例,
                // 将这个实例分解,分解,
                // 获取上面的最上面的RequestMapping
                // 获取每个方法上的RequestMapping
                // 将其组合一下,然后将其组合一下,
                // TODO
                // 老子突然间发现,写这玩意,貌似不是特别难啊.....
                //// https://github.com/513667225/luban-register-center.git..进入误区了...再重新思考一下,该怎么整..

//                controllerBean.getClass()


            }

        }


    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
