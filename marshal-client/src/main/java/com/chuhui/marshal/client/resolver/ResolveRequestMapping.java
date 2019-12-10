package com.chuhui.marshal.client.resolver;

import com.chuhui.marshal.framework.transfer.service.ServiceDefinition;
import com.chuhui.marshal.framework.transfer.service.UrlServiceDefinition;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

import static com.chuhui.marshal.framework.utils.Constant.*;
import static com.chuhui.marshal.framework.utils.DataCheckUtils.arrayDeduplication;

/**
 * ResolveRequestMapping
 * <p>
 * 解析requestMapping
 *
 * @author: 纯阳子
 * @date: 2019/12/7
 */
class ResolveRequestMapping {


    enum REQUEST_MAPPING {
        /**
         * GetMapping注解的封装
         */
        GETMAPPING(GetMapping.class),
        /**
         * PostMapping注解的封装
         */
        POSTMAPPING(PostMapping.class),
        /**
         * PostMapping注解的封装
         */
        PUTMAPPING(PutMapping.class),
        /**
         * DeleteMapping注解的封装
         */
        DELETEMAPPING(DeleteMapping.class),
        /**
         * PatchMapping注解的封装
         */
        PATCHMAPPING(PatchMapping.class);
        Class<? extends Annotation> key;

        REQUEST_MAPPING(Class<? extends Annotation> clazz) {
            key = clazz;
        }

        static REQUEST_MAPPING valueOfClass(Class<? extends Annotation> clazz) {
            REQUEST_MAPPING[] values = values();

            for (REQUEST_MAPPING mapping : values) {
                if (mapping.equals(clazz)) {
                    return mapping;
                }
            }
            return null;
        }

        public Class<? extends Annotation> getKey() {
            return key;
        }
    }


    private static final Class<? extends Annotation> MAIN_REQUEST_MAPPING_CLASS = RequestMapping.class;

    /**
     * 这个函数功能是专门用来解析方法级别上的RequestMapping,RequestMapping包含GetMapping,PostMapping...
     * <p>
     * 如果方法上没有获取到 {@code RequestMapping}则,直接返回false.解析失败.
     *
     * @param method     需要解析RequestMapping的方法
     * @param definition 数据解析后,存入对象中
     * @return 返回是否解析成功
     */
    static boolean parseRequestMapping(final Method method, final ServiceDefinition definition) {

        Annotation annotation = getMethodRequestMappingAnnotation(method);

        if (annotation == null) {
            return false;
        } else {
            definition.setMethodName(method.getName());
            getPartProperties(annotation, definition);
            return true;
        }
    }

    /**
     * 获取方法上的和{@link RequestMapping @RequestMapping}相关的注解,包括
     * {@link RequestMapping @RequestMapping},
     * {@link GetMapping @GetMapping},
     * {@link PostMapping @PostMapping},
     * {@link DeleteMapping @DeleteMapping},
     * {@link PatchMapping @PatchMapping},
     * {@link PutMapping @PutMapping},
     *
     * @param method 获取注解的方法
     * @return 如果没有获取到上述6个注解之一, 则返回{@code null}.
     */
    private static Annotation getMethodRequestMappingAnnotation(Method method) {
        // 如果获取到了@RequestMapping注解,则直接返回
        Annotation annotation = method.getAnnotation(MAIN_REQUEST_MAPPING_CLASS);
        if (annotation != null) {
            return annotation;
        }

        for (REQUEST_MAPPING mappingClass : REQUEST_MAPPING.values()) {
            annotation = method.getAnnotation(mappingClass.getKey());
            if (annotation != null) {
                return annotation;
            }
        }
        return null;
    }


    /**
     * 从注解中解析出部分属性,为{@code ServiceDefinition}赋值
     * 1.设置请求方法,post,get等
     * 2.设置方法上的请求路径
     * 3.设置方法上的注解
     *
     * @param annotation 需要解析的注解
     * @param definition asda
     */
    private static void getPartProperties(Annotation annotation, ServiceDefinition definition) {

        if (annotation.annotationType().equals(MAIN_REQUEST_MAPPING_CLASS)) {

            // 处理@RequestMapping注解上的数据
            // 获取@RequestMapping注解上的请求方法,比如:GET,POST....
            // 因为@RequestMapping注解上可以有多个请求方法,
            // 不同于其他具体的如@GetMapping,@PostMapping等只有唯一请求方法的注解,
            // 所以需要进行设置解析
            definition.setRequestMethod(arrayDeduplication(requestMethod(annotation)));
        } else {
            // 2019-12-09 22:48:46
            // todo 这段代码是不是多余呢?
            Annotation mainRequestMapping = AnnotationUtils.getAnnotation(annotation, MAIN_REQUEST_MAPPING_CLASS);
            definition.setRequestMethod(requestMethod(mainRequestMapping));
        }

        definition.setMethodPath(arrayDeduplication(requestMapping(annotation)));
        definition.setServiceRequestAnnotation(annotation);
    }


    private static String[] requestMapping(Annotation requestMapping) {

        Class<? extends Annotation> annotationClass = requestMapping.annotationType();

        Method pathMethod = ReflectionUtils.findMethod(annotationClass, REQUEST_MAPPING_PATH_METHOD_NAME);
        Object pathValue = ReflectionUtils.invokeMethod(pathMethod, requestMapping);
        String[] pathValueResult = (String[]) pathValue;

        if (ArrayUtils.isNotEmpty(pathValueResult)) {
            return pathValueResult;
        }
        Method valueMethod = ReflectionUtils.findMethod(annotationClass, REQUEST_MAPPING_VALUE_METHOD_NAME);
        Object valueValue = ReflectionUtils.invokeMethod(valueMethod, requestMapping);
        String[] valueValueResult = (String[]) valueValue;
        if (ArrayUtils.isNotEmpty(valueValueResult)) {
            return valueValueResult;
        }

        // 某些RequestMapping注解上可能没有设置value或者path
        return new String[]{"/"};
    }


    /**
     * 从给定的注解上获取{@link RequestMethod RequestMethod}实例.
     * 如果定的注解获取不到{@link RequestMapping RequestMapping}注解,则抛出异常.
     * 如果从给定的注解上解析无法解析出{@link RequestMethod RequestMethod}实例,则返回{@code null}.
     *
     * @param requestMapping 需要解析出{@link RequestMethod RequestMethod}实例的注解
     * @return 如果能解析出 {@code RequestMethod}实例,则返回解析出的实例,否则返回{@code null}
     */
    private static String[] requestMethod(Annotation requestMapping) {

        RequestMapping annotation = AnnotationUtils.getAnnotation(requestMapping, RequestMapping.class);

        if (null != annotation) {
            RequestMethod[] requestMethods = annotation.method();

            if (ArrayUtils.isNotEmpty(requestMethods)) {
                String[] methods = new String[requestMethods.length];
                for (int i = 0; i < requestMethods.length; i++) {
                    methods[i] = requestMethods[i].toString();
                }
                return methods;
            } else {
                RequestMethod[] values = RequestMethod.values();
                String[] methods = new String[values.length];
                for (int i = 0; i < values.length; i++) {
                    methods[i] = values[i].toString();
                }
                return methods;
            }
        }

        return null;
    }

    /**
     * 将{@code ServiceDefinition}中的{@code mainPath}和{@code methodPath}进行组合
     *
     * @param mainDefinitions
     * @return
     */
    static List<UrlServiceDefinition> disintegrateServiceDefinition(List<ServiceDefinition> mainDefinitions) {


        List<UrlServiceDefinition> result = mainDefinitions.stream().map(definition -> {

            List<UrlServiceDefinition> serviceDefinitions = new ArrayList<>();

            for (String mainPath : definition.getMainPath()) {


                if (!mainPath.startsWith(URL_DELIMITER)) {
                    mainPath = URL_DELIMITER + mainPath;
                }

                for (String methodPath : definition.getMethodPath()) {

                    UrlServiceDefinition serviceDefinition = new UrlServiceDefinition();

                    serviceDefinition.setClassName(definition.getClassName());
                    serviceDefinition.setMethodName(definition.getMethodName());
                    serviceDefinition.setRequestMethod(definition.getRequestMethod());
                    serviceDefinition.setServiceRequestAnnotation(definition.getServiceRequestAnnotation().annotationType().getTypeName());


                    if (StringUtils.isEmpty(methodPath)) {
                        serviceDefinition.setServiceUrl(mainPath + URL_DELIMITER);
                    } else if (methodPath.startsWith(URL_DELIMITER)) {
                        serviceDefinition.setServiceUrl(mainPath + methodPath);
                    } else {
                        serviceDefinition.setServiceUrl(mainPath + URL_DELIMITER + methodPath);
                    }
                    serviceDefinitions.add(serviceDefinition);
                }
            }
            return serviceDefinitions;
        }).flatMap(List::stream).collect(Collectors.toList());

        return result;
    }


}
