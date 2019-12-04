package com.chuhui.marshal.examplclient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 *
 * @author: cyzi
 * @Date: 2019/12/4 0004
 * @Description:TODO
 */
@RestController
public class UserController {



    @GetMapping("/userOrder/{userName}")
    public void getUserOrder(@PathVariable String userName){

    }

}
