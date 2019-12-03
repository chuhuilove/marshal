package com.chuhui.marshal.examplserver;

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
@EnableMarshal(value = "server-1", roles = EnableMarshal.ROLES.SERVER, serverAddress = "127.0.0.1:1125")
@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(ServerApplication.class);

        application.run(args);
    }
}