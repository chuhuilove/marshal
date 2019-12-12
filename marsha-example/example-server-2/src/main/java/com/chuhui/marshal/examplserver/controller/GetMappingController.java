package com.chuhui.marshal.examplserver.controller;

import org.springframework.core.Ordered;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * GetMappingController
 *
 * @author: cyzi
 * @Date: 2019/12/9 0009
 * @Description:TODO
 */
@RestController
@RequestMapping(method = {RequestMethod.DELETE, RequestMethod.PUT})
public class GetMappingController implements Ordered {

    @PostConstruct
    public void init(){
        System.err.println("GetMappingController  init");
    }


    @GetMapping("/function")
    public String function() {
        return " uuid is:" + UUID.randomUUID().toString();
    }


    @Override
    public int getOrder() {
        return 3;
    }
}
