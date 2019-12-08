package com.chuhui.examplespringmvcwithoutwebxml;

import com.chuhui.examplespringmvcwithoutwebxml.config.AppConfig;
import com.chuhui.examplespringmvcwithoutwebxml.config.WebMvcConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * ApplicationBootstrap
 * <p>
 * //TODO description
 *
 * @author: 纯阳子
 * @date: 2019/12/8
 */
public class ApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletCxt) throws ServletException {

        /**
         * 初始化Spring 环境
         */

        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();

        rootContext.register(AppConfig.class);
        rootContext.refresh();


        servletCxt.addListener(new ContextLoaderListener(rootContext));
        servletCxt.setInitParameter("defaultHtmlEscape", "true");

        /**
         * 初始化 Spring web环境
         */
        // now the config for the Dispatcher servlet
        AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
        mvcContext.register(WebMvcConfig.class);

        /**
         * 添加{#@link DispatcherServlet}
         */
        DispatcherServlet servlet = new DispatcherServlet(mvcContext);
        // 没有找到请求,不走默认的流程
        // 走到自定义的异常处理器中
        servlet.setThrowExceptionIfNoHandlerFound(true);
        ServletRegistration.Dynamic dispatcherServlet =
                servletCxt.addServlet("dispatcherServlet", servlet);
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");


        /**
         * 当提交表单并且表单中有中文的时候,默认情况下的会出现乱码
         * 配置此过滤器能能避免乱码的情况出现
         * 配置过滤器,编码过滤器
         */
        FilterRegistration.Dynamic encodingFilter =
                servletCxt.addFilter("encodingFilter", CharacterEncodingFilter.class);
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(null, true, "/*");



    }
}
