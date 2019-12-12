package com.chuhui.marshal.examplserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * GetMappingController
 *
 * @author: cyzi
 * @Date: 2019/12/9 0009
 * @Description:TODO
 */
@RestController
@RequestMapping(value = "demoContr",method = {RequestMethod.DELETE, RequestMethod.PUT})
public class GetMappingController {



    @RequestMapping("/function2")
    public String function() {
        return " uuid is:" + UUID.randomUUID().toString();
    }

    @RequestMapping()
    public String getTestNull() {
        return "nullGetMapping" + UUID.randomUUID().toString().replaceAll("-", "");
    }
    @GetMapping("/function")
    public String getTestNull12345() {
        return "getTestNull12345" + UUID.randomUUID().toString().replaceAll("-", "");
    }

}
