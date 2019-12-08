package com.chuhui.marshal.client.utils;

import com.chuhui.marshal.framework.transfer.ServiceDefinition;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.chuhui.marshal.framework.utils.Constant.REQUEST_MAPPING_PATH_METHOD_NAME;
import static com.chuhui.marshal.framework.utils.Constant.REQUEST_MAPPING_VALUE_METHOD_NAME;

/**
 * ResolveRequestMapping
 * <p>
 * 解析requestMapping
 *
 * @author: 纯阳子
 * @date: 2019/12/7
 */
public class ResolveRequestMapping {


    /**
     * 这个函数功能是专门用来解析方法级别上的RequestMapping,RequestMapping包含GetMapping,PostMapping...
     * <p>
     * 如果方法上没有获取到 {@code RequestMapping}则,直接返回false.解析失败.
     * 获取注解的方法AnnotationUtils.getAnnotation(method, RequestMapping.class),返回{@code null},
     * 则意味着{@code method}上既没有{@code RequestMapping}注解,也没有{@code GetMapping}..等注解.
     * <p>
     * //todo 注释还需要继续写一下...
     *
     * @param method     需要解析RequestMapping的方法
     * @param definition 数据解析后,存入对象中
     * @return 返回是否解析成功
     */
    public static boolean parseRequestMapping(final Method method, final ServiceDefinition definition) {

        definition.setMethodName(method.getName());


        Annotation annotation = AnnotationUtils.getAnnotation(method, RequestMapping.class);
        // 如果找不到RequestMapping注解,则代表也找不到GetMapping等注解
        // 这是Spring的AnnotationUtils特有的功能
        if (annotation == null) {
            return false;
        }
        getPartProperties(annotation, definition);
        if (ArrayUtils.isNotEmpty(definition.getMethodPath())) {
            return true;
        }

        annotation = AnnotationUtils.getAnnotation(method, GetMapping.class);
        getPartProperties(annotation, definition);
        if (ArrayUtils.isNotEmpty(definition.getMethodPath())) {
            return true;
        }

        annotation = AnnotationUtils.getAnnotation(method, PostMapping.class);
        getPartProperties(annotation, definition);
        if (ArrayUtils.isNotEmpty(definition.getMethodPath())) {
            return true;
        }

        annotation = AnnotationUtils.getAnnotation(method, PutMapping.class);
        getPartProperties(annotation, definition);
        if (ArrayUtils.isNotEmpty(definition.getMethodPath())) {
            return true;
        }


        annotation = AnnotationUtils.getAnnotation(method, DeleteMapping.class);
        getPartProperties(annotation, definition);
        if (ArrayUtils.isNotEmpty(definition.getMethodPath())) {
            return true;
        }

        annotation = AnnotationUtils.getAnnotation(method, PatchMapping.class);
        getPartProperties(annotation, definition);
        return ArrayUtils.isNotEmpty(definition.getMethodPath());

    }


    private static void getPartProperties(Annotation annotation, ServiceDefinition definition) {
        definition.setMethodPath(stringArray(requestMapping(annotation)));
        definition.setServiceRequestAnnotation(annotation);
        definition.setRequestMethod(stringArray(requestMethod(annotation)));
    }


    private static String[] requestMapping(Annotation requestMapping) {
        if (requestMapping == null) {
            return null;
        } else {
            Class<? extends Annotation> annotationClass = requestMapping.annotationType();


            Method pathMethod = ReflectionUtils.findMethod(annotationClass, REQUEST_MAPPING_PATH_METHOD_NAME);
            Object pathValue = ReflectionUtils.invokeMethod(pathMethod, requestMapping);
            String[] pathValueResult = (String[]) pathValue;

            if (ArrayUtils.isNotEmpty(pathValueResult)) {
                return pathValueResult;
            }
            Method valueMethod = ReflectionUtils.findMethod(annotationClass, REQUEST_MAPPING_VALUE_METHOD_NAME);
            Object valueValue = ReflectionUtils.invokeMethod(valueMethod, requestMapping);
            String[] valueValueresult = (String[]) valueValue;
            if (ArrayUtils.isNotEmpty(valueValueresult)) {
                return valueValueresult;
            }

            // 某些RequestMapping注解上可能没有设置value或者path
            return new String[]{""};
        }
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
        if (null == requestMapping) {
            return null;
        }
        RequestMapping annotation = AnnotationUtils.getAnnotation(requestMapping, RequestMapping.class);

        if (null != annotation) {
            RequestMethod[] requestMethods = annotation.method();
            if (ArrayUtils.isNotEmpty(requestMethods)) {
                String[] methods = new String[requestMethods.length];
                for (int i = 0; i < requestMethods.length; i++) {
                    methods[i] = requestMethods[i].toString();
                }
                return methods;
            }
        }
        return null;
    }

    private static String[] stringArray(String[] strs) {
        Set<String> sets = new HashSet<>();

        if (ArrayUtils.isNotEmpty(strs)) {
            sets.addAll(Arrays.asList(strs));

            return sets.toArray(new String[sets.size()]);
        }
        return strs;
    }

}
