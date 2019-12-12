package com.chuhui.marshal.examplserver.controller;

import org.springframework.core.Ordered;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
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
public class TestCustomeController implements Ordered {


    @PostConstruct
    public void init(){
        System.err.println("TestCustomeController  init");
    }


    @GetMapping("/getAll.do")
    public String getAlll() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    @RequestMapping()
    public String getTestNull(){
        return "nullGetMapping"+UUID.randomUUID().toString().replaceAll("-", "");
    }


    @Override
    public int getOrder() {
        return 1;
    }
}
