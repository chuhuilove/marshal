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
@RequestMapping(method = {RequestMethod.DELETE, RequestMethod.PUT})
public class GetMappingController {


    @GetMapping("/function")
    public String function() {
        return " uuid is:" + UUID.randomUUID().toString();
    }


}
