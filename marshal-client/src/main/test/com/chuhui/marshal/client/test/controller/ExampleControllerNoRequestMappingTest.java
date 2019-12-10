package com.chuhui.marshal.client.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * ExampleControllerNoRequestMappingTest
 *
 * controller示例
 *
 * @author: cyzi
 * @Date: 2019/12/9 0009
 * @Description:TODO
 */
@RestController
public class ExampleControllerNoRequestMappingTest {

    /**
     * 不设置method,不设置value,不设置path
     * @return
     */
    @RequestMapping()
    public String exampleMethod1(){
        return "exampleMethod1"+ UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 不设置method,设置多个value,不设置path
     * @return
     */
    @RequestMapping({"requestPath1","requestPath2"})
    public String exampleMethod2(){
        return "exampleMethod2"+ UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     *  不设置method,不设置value,设置path
     * @return
     */
    @RequestMapping(path = {"requestPath1","requestPath2"})
    public String exampleMethod3(){
        return "exampleMethod3"+ UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 设置多个method,设置多个path
     * @return
     */
    @RequestMapping(path = {"requestPath1","requestPath2"},method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
    public String exampleMethod4(){
        return "exampleMethod4"+ UUID.randomUUID().toString().replaceAll("-","");
    }





}
