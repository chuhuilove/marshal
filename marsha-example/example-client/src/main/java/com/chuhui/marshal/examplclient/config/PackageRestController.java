package com.chuhui.marshal.examplclient.config;

import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * PackageRestController
 * <p>
 * //TODO description
 *
 * @author: 纯阳子
 * @date: 2019/12/5
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
public @interface PackageRestController {
}
