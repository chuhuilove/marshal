package com.chuhui.marshal.examplserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
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
        return "/getAll.do"+UUID.randomUUID().toString().replaceAll("-", "");
    }

    @GetMapping("/getAll.do/{userName}")
    public String getNotpp() {
        return "/getAll.do/"+UUID.randomUUID().toString().replaceAll("-", "");
    }

    @RequestMapping()
    public String getTestNull() {
        return "nullGetMapping" + UUID.randomUUID().toString().replaceAll("-", "");
    }

}
