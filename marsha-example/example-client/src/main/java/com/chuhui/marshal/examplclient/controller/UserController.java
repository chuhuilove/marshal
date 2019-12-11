package com.chuhui.marshal.examplclient.controller;

import com.chuhui.marshal.client.caller.MarshalClientCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * UserController
 *
 * @author: cyzi
 * @Date: 2019/12/4 0004
 * @Description:TODO
 */
@RestController
public class UserController {

    @Autowired
    private MarshalClientCaller serviceCaller;

    private static final String EXAMPLE_SERVER_GROUP="example-server";

    @GetMapping("/userOrder/{userName}")
    public void getUserOrder(@PathVariable String userName) {

        serviceCaller.get(EXAMPLE_SERVER_GROUP,"/orderMain/orderService/",userName,String.class);

//        String str = template.getForObject("http://127.0.0.1:9632/example-server/orderServiceOne/" + userName, String.class);
//        System.err.println(str);
//        ResponseEntity<Integer> integerResponseEntity = template.postForEntity("http://127.0.0.1:9632/example-server/postRequest", str, int.class);
//        System.err.println(integerResponseEntity.getBody());

    }

}
