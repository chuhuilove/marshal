package com.chuhui.marshal.examplserver;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * BeanFactoryHandler
 *
 * @author: cyzi
 * @Date: 2019/12/5 0005
 * @Description:TODO
 */

public class BeanFactoryHandler implements BeanFactoryPostProcessor {

    final static private Logger logger = LoggerFactory.getLogger(BeanFactoryHandler.class);


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        String[] beanNames = beanFactory.getBeanDefinitionNames();


        if (ArrayUtils.isNotEmpty(beanNames)) {

            for (String beanName : beanNames) {
//                System.err.println(beanName);
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);

                System.err.println(beanName+"------>"+beanDefinition.getBeanClassName());

            }

//            orderController
//            testCustomeController



        }


    }
}
