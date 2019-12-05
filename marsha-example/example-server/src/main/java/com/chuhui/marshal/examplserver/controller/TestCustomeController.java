package com.chuhui.marshal.examplserver.controller;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.util.Arrays;
import java.util.UUID;

/**
 * TestCustomeController
 *
 * @author: cyzi
 * @Date: 2019/12/5 0005
 * @Description:TODO
 */
@CustomeController
@RequestMapping("cyziCustomeController")
public class TestCustomeController {


    @GetMapping("/getAll.do")
    public String getAlll() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    public static void main(String[] args) {

        try {
            boolean annotationPresent = Class.forName(TestCustomeController.class.getName()).isAnnotationPresent(RestController.class);



            System.err.println(annotationPresent);


            Annotation[] annotations = Class.forName(TestCustomeController.class.getName()).getDeclaredAnnotations();

            if (ArrayUtils.isNotEmpty(annotations)) {

                for (Annotation annotation : annotations) {
                    // com.chuhui.marshal.examplserver.controller.CustomeController
//                    System.err.println("==========" + annotation.annotationType().getName());

                    if (searchControllerAnno(annotation)) {
                        System.err.println("====有Controller注解======" + annotation.annotationType().getName());
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static boolean searchControllerAnno(Annotation annotation) {

        if (annotation.annotationType().equals(Controller.class) || annotation.annotationType().equals(RestController.class)) {
            return true;
        }
        Annotation[] declaredAnnotations = annotation.annotationType().getDeclaredAnnotations();

        if (ArrayUtils.isNotEmpty(declaredAnnotations)) {

            for (Annotation ann : declaredAnnotations) {
                return searchControllerAnno(ann);
            }
        }
        return false;
    }

}
