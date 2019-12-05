package com.chuhui.marshal.examplserver.controller;

import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * CustomeController
 *
 * @author: cyzi
 * @Date: 2019/12/5 0005
 * @Description:TODO
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
public @interface CustomeController {
}
