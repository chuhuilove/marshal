package com.chuhui.marshal.examplclientserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * AppConfig
 *
 * @author: cyzi
 * @Date: 2019/12/4 0004
 * @Description:TODO
 */
@Configuration
public class AppConfig {


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
