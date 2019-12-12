package com.chuhui.marshal.client.resolver;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * ResolveRequestMapping
 * <p>
 * 解析requestMapping
 *
 * @author: 纯阳子
 * @date: 2019/12/7
 */
class ResolveRequestMapping {


    /**
     * 从method中解析出{@code @RequestMapping}注解里面的数据
     * {@link org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping#getMappingForMethod}
     * @param method 要解析的方法
     * @param userType method所在的类
     * @return
     */
    static RequestMappingInfo getMappingForMethod(Method method, Class<?> userType) {

        // 先获取注解在方法上的RequestMapping
        RequestMappingInfo info = createRequestMappingInfo(method);
        if (Objects.nonNull(info)) {
            // 获取注解在类上的RequestMapping
            RequestMappingInfo typeInfo = createRequestMappingInfo(userType);
            if (Objects.nonNull(typeInfo)) {
                // 两个info进行合并
                info = typeInfo.combine(info);
            }
        }
        return info;
    }


    @Nullable
    private static RequestMappingInfo createRequestMappingInfo(AnnotatedElement element) {
        RequestMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(element, RequestMapping.class);
        if (requestMapping == null) {
            return null;
        }
        RequestMappingInfo.Builder builder = RequestMappingInfo
                .paths((requestMapping.path()))
                .methods(requestMapping.method())
                .params(requestMapping.params())
                .headers(requestMapping.headers())
                .consumes(requestMapping.consumes())
                .produces(requestMapping.produces())
                .mappingName(requestMapping.name());

        return builder.options(new RequestMappingInfo.BuilderConfiguration()).build();
    }

    static class MethodRequestMapping {

        final Method method;
        final RequestMappingInfo mappingInfo;

        MethodRequestMapping(Method method, RequestMappingInfo mappingInfo) {
            this.method = method;
            this.mappingInfo = mappingInfo;
        }
    }


}
