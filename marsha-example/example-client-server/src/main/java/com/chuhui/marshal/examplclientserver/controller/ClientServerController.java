package com.chuhui.marshal.examplclientserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * ClientServerController
 *
 * @author: cyzi
 * @Date: 2019/12/4 0004
 * @Description:TODO
 */
@RestController
public class ClientServerController {

    @Autowired
    private RestTemplate template;


    @GetMapping("/invoke")
    public String invokeMethod() {





        return UUID.randomUUID().toString().replaceAll("-", "");
    }


}
