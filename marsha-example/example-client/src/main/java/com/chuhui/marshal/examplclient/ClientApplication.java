package com.chuhui.marshal.examplclient;

import com.chuhui.marshal.client.annotation.EnableMarshalConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClientApplication
 * 消费者示例
 *
 * @author: cyzi
 * @Date: 2019/12/3 0003
 * @Description:
 */
@SpringBootApplication
@EnableMarshalConsumer(name = "consumer-1",
        // 这个东西的目的,是为了服务粒度更细一点
        // 但是实际结果上,这个东西感觉很鸡肋
        marshalServer = {"127.0.0.1:1125","127.0.0.1:1126"})
public class ClientApplication {

    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(ClientApplication.class);
        application.run(args);
    }
}
