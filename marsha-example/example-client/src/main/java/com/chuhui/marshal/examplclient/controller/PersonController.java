package com.chuhui.marshal.examplclient.controller;

import com.chuhui.marshal.examplclient.config.PackageRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

/**
 * PersonController
 * <p>
 * //TODO description
 *
 * @author: 纯阳子
 * @date: 2019/12/5
 */
@PackageRestController
public class PersonController {
    @Autowired
    private RestTemplate template;

    @PostMapping("/person")
    public void getUserOrder() {

        System.err.println("lalalalal");
        System.err.println("lalalalal");
        System.err.println("lalalalal");
        System.err.println("lalalalal");

    }

}
