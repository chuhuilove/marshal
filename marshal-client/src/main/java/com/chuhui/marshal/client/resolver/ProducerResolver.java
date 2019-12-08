package com.chuhui.marshal.client.resolver;

import com.chuhui.marshal.client.AbstractAnnotationResolver;
import com.chuhui.marshal.client.annotation.EnableMarshalProducer;
import com.chuhui.marshal.framework.transfer.ServiceDefinition;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.chuhui.marshal.client.utils.ResolveRequestMapping.parseRequestMapping;
import static com.chuhui.marshal.framework.utils.Constant.SPRING_FRAMEWORK_CONTROLLER_PREFIX;

/**
 * ResolveEnableMarshalProducer
 * {@code EnableMarshalProducer}注解解析器
 * <p>
 * 生产者解析器
 * 启用基于全注解的风格...
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


        List<ServiceDefinition> definitions = resolveController();

        // 已经获取到所有带有RequestMapping的方法了...
        // todo 2019年12月9日00:05:16
        // todo 将ServiceDefinition 中的mainPath,methodPath进行组合.



//        startRemoteClient(marshalServer);
    }

    private List<ServiceDefinition> resolveController() {


        /*
         * getBeansWithAnnotation方法获取所有已经在容器内注册的带有Controller注解的类
         * 有一个小问题,在spring-boot中,出现了一个
         * org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController类
         *
         * 根据逻辑,我们不认为这个类应该被我们解析
         * 但是我们提供了一个属性,来进行全局设置,
         *
         *
         */
        Map<String, Object> coreBeans = context.getBeansWithAnnotation(Controller.class);



        // 服务所在组 group+一级RequestMapping+二级RequestMapping

        Collection<Object> values = coreBeans.values();
        final List<ServiceDefinition> definitions = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(values)) {
            values.forEach(e -> {

                String className = e.getClass().getName();

                if (className.startsWith(SPRING_FRAMEWORK_CONTROLLER_PREFIX) && register) {
                    // 允许注册以org.springframework.开头的controller
                    resolveControllerInstance(e, definitions, className);

                } else if (!className.startsWith(SPRING_FRAMEWORK_CONTROLLER_PREFIX)) {
                    // 不是以org.springframework.开头的类
                    resolveControllerInstance(e, definitions, className);
                }
            });
        }

        return definitions;
    }

    private void resolveControllerInstance(Object e, List<ServiceDefinition> definitions, String className) {

        RequestMapping annotation = AnnotationUtils.findAnnotation(e.getClass(), RequestMapping.class);
        String[] mainPaths = requestMappingPath(annotation);
        Method[] methods = e.getClass().getDeclaredMethods();

        if (ArrayUtils.isNotEmpty(methods)) {

            for (Method method : methods) {
                ServiceDefinition serviceDefinition = new ServiceDefinition(className, mainPaths);

                // 需要判端一下,是不是需要将service添加到service中....
                if (parseRequestMapping(method, serviceDefinition)) {
                    definitions.add(serviceDefinition);
                }
            }
        }


    }


    private static String[] requestMappingPath(RequestMapping requestMapping) {
        if (requestMapping == null) {
            return new String[]{"/"};
        } else {
            String[] path = requestMapping.path();
            if (ArrayUtils.isNotEmpty(path)) {
                return path;
            } else {
                String[] value = requestMapping.value();
                return ArrayUtils.isNotEmpty(value) ? value : null;
            }
        }
    }


    private String group;
    private String[] marshalServer;
    private String selfAddress;
    private String value;
    private boolean register;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        EnableMarshalProducer annotatedBeanName = getAnnotatedBeanName(applicationContext, EnableMarshalProducer.class);

        group = annotatedBeanName.group();
        marshalServer = annotatedBeanName.marshalServer();
        selfAddress = annotatedBeanName.selfAddress();
        value = annotatedBeanName.value();
        context = applicationContext;
        register = annotatedBeanName.register();

    }
}
