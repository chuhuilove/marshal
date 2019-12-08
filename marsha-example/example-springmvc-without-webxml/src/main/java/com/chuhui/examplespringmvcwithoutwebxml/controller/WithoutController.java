package com.chuhui.examplespringmvcwithoutwebxml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * WithoutController
 * <p>
 * //TODO description
 *
 * @author: 纯阳子
 * @date: 2019/12/8
 */
@RestController
@RequestMapping("withoutController")
public class WithoutController {

    @Autowired
    private ApplicationContext context;

    @RequestMapping()
    public String getWithoutConTest() {
        return context.getApplicationName()+"<====>"+UUID.randomUUID().toString();

    }

}
