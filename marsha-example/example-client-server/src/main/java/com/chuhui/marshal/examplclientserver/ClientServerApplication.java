package com.chuhui.marshal.examplclientserver;

import com.chuhui.marshal.client.annotation.EnableMarshalClient;
import com.chuhui.marshal.client.annotation.EnableMarshalServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClientServerApplication
 * 微服务既可以作为生产者,又可以作为消费者.
 * 这个该怎么设计呢???
 * @author: cyzi
 * @Date: 2019/12/4 0004
 * @Description:
 */

@EnableMarshalClient
@EnableMarshalServer
@SpringBootApplication
public class ClientServerApplication {

    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(ClientServerApplication.class);
        application.run(args);
    }

}
