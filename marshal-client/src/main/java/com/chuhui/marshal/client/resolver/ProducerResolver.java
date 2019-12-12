package com.chuhui.marshal.client.resolver;

import com.chuhui.marshal.client.AbstractAnnotationResolver;
import com.chuhui.marshal.client.annotation.EnableMarshalProducer;
import com.chuhui.marshal.framework.transfer.google.ProducerRequestPackage;
import com.chuhui.marshal.framework.transfer.google.ServiceDefinitionPackage;
import com.chuhui.marshal.framework.transfer.service.ServiceDefinition;
import com.chuhui.marshal.framework.transfer.service.UrlServiceDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodIntrospector.MetadataLookup;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.util.*;
import java.util.stream.Collectors;

import static com.chuhui.marshal.client.resolver.ResolveRequestMapping.MethodRequestMapping;
import static com.chuhui.marshal.client.resolver.ResolveRequestMapping.getMappingForMethod;
import static com.chuhui.marshal.framework.utils.Constant.SPRING_FRAMEWORK_CONTROLLER_PREFIX;
import static com.chuhui.marshal.framework.utils.Constant.URL_DELIMITER;
import static org.springframework.core.MethodIntrospector.selectMethods;

/**
 * ResolveEnableMarshalProducer
 * {@code EnableMarshalProducer}注解解析器
 * <p>
 * 生产者解析器
 * 启用基于全注解的风格...
 *
 * <p>
 * 需要解析的注解,有如下几种.
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
        resolveController();
       /* List<ServiceDefinition> definitions = resolveController();

        List<UrlServiceDefinition> serviceDefinitions = disintegrateServiceDefinition(definitions);

        startRemoteClient(marshalServer);
        ProducerRequestPackage requestPackage = buildRegisterPackage(serviceDefinitions);
        clientContextFactory.sendMessage(CLIENT_REMOTE_REQUEST_FLAG.PRODUCER_FIRST_REQUEST, requestPackage.toByteArray());
*/
    }

    private ProducerRequestPackage buildRegisterPackage(List<UrlServiceDefinition> serviceDefinitions) {
        final ProducerRequestPackage.Builder builder = ProducerRequestPackage.newBuilder()
                .setSelfAddress(selfAddress)
                .setServerGroup(group)
                .setServerName(value);

        serviceDefinitions.forEach(e -> {
            ServiceDefinitionPackage build = ServiceDefinitionPackage.newBuilder()
                    .setClassName(e.getClassName())
                    .setMethodName(e.getMethodName())
                    .setServiceUrl(e.getServiceUrl())
                    .setServiceRequestAnnotation(e.getServiceRequestAnnotation())
                    .addAllRequestMethod(Arrays.asList(e.getRequestMethod()))
                    .setServiceName(e.getServiceUrl().replaceAll(URL_DELIMITER, "."))
                    .build();

            // 设置新增数据

            builder.addDefinitions(build);
        });

        return builder.build();
    }

    /**
     * 从context工厂中获取需要解析的controller类
     *
     * @return
     */
    private List<ServiceDefinition> resolveController() {


        /*
         * getBeansWithAnnotation方法获取所有已经在容器内注册的带有Controller注解的类
         * 有一个小问题,在spring-boot中,出现了一个
         * org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController类
         *
         * 根据逻辑,我们不认为这个类应该被我们解析
         * 但是我们提供了一个属性,来进行全局设置,
         *
         */
        Map<String, Object> coreBeans = context.getBeansWithAnnotation(Controller.class);

        Collection<Object> values = coreBeans.values();


        final List<ServiceDefinition> definitions = new ArrayList<>();


        List<MethodRequestMapping> methodRequestMappings = values.stream().map((value) -> {
            Class<?> userType = ClassUtils.getUserClass(value.getClass());
            String className = userType.getName();
            if (!isSpring(className) || register) {
                return resolveMethodAndRequestMapping(userType);
            }
            return null;
        }).filter(Objects::nonNull).flatMap(List::stream).collect(Collectors.toList());

        methodRequestMappings.forEach(e -> {
            System.err.println(e.method.getName());


            RequestMappingInfo mappingInfo = e.mappingInfo;

            // 请求方法
            Set<RequestMethod> methods = mappingInfo.getMethodsCondition().getMethods();

            Set<String> patterns = mappingInfo.getPatternsCondition().getPatterns();

            System.err.println(methods);

            System.err.println(patterns);


            System.err.println("============================");

            // 重新设计...

        });





        return definitions;
    }


    private boolean isSpring(String className) {
        return className.startsWith(SPRING_FRAMEWORK_CONTROLLER_PREFIX);
    }

    private List<MethodRequestMapping> resolveMethodAndRequestMapping(final Class<?> classType) {

        return selectMethods(classType, (MetadataLookup<RequestMappingInfo>) method -> getMappingForMethod(method, classType))
                .entrySet().stream().map(e -> new MethodRequestMapping(e.getKey(), e.getValue())).collect(Collectors.toList());

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

        if (!group.startsWith(URL_DELIMITER)) {
            group = URL_DELIMITER + group;
        }
        marshalServer = annotatedBeanName.marshalServer();
        selfAddress = annotatedBeanName.selfAddress();
        value = annotatedBeanName.value();
        context = applicationContext;
        register = annotatedBeanName.register();

//        ServerProperties bean = applicationContext.getBean(ServerProperties.class);
//        Integer port = bean.getPort();
//        String contextPath = bean.getServlet().getContextPath();
//        System.err.println("this is debugger");

        // 整体设计有些冗余....
        // 牵扯的类太多了
        // 今天先完成调用,其他的再说吧....

    }
}
