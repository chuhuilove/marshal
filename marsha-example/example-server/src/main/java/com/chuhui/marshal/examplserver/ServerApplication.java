package com.chuhui.marshal.examplserver;

import com.chuhui.marshal.client.annotation.EnableMarshalProducer;
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
//@EnableMarshalProducer(value = "example-server-1", group = "example-server", marshalServer = {"127.0.0.1:1125", "127.0.0.1:1126"}, selfAddress = "127.0.0.1:9632")
@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(ServerApplication.class);

        application.run(args);
    }
}
