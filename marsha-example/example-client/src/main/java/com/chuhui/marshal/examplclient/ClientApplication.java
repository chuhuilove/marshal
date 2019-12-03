package com.chuhui.marshal.examplclient;

import com.chuhui.marshal.client.EnableMarshal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ServerApplication
 * 示例服务
 *
 * @author: cyzi
 * @Date: 2019/12/3 0003
 * @Description:TODO
 */
@EnableMarshal(value = "client-1", roles = EnableMarshal.ROLES.CLIENT, serverAddress = "127.0.0.1:11255")
@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(ClientApplication.class);

        application.run(args);
    }
}
