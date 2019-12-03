package com.chuhui.marshal.examplserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OrderController
 * <p>
 *
 * @author: 纯阳子
 * @date: 2019/12/3
 */
@RestController
public class OrderController {

    @GetMapping("/orderService/{userName}")
    public String orderService(@PathVariable String userName) {
        return System.nanoTime() + userName;
    }

}
