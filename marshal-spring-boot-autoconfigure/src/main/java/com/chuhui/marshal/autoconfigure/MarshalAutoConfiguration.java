package com.chuhui.marshal.autoconfigure;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * MarshalAutoConfiguration
 *
 * @author: cyzi
 * @Date: 2020/1/9 0009
 * @Description: marshal的自动配置类
 */
@Configuration
@AutoConfigureBefore(JmsAutoConfiguration.class)
//@ConditionalOnClass({ConnectionFactory.class, HornetQJMSClient.class})
//@ConditionalOnMissingBean(ConnectionFactory.class)
@EnableConfigurationProperties(MarshalProperties.class)
public class MarshalAutoConfiguration {


    // 这里编写ye

// 现在该怎么设计,都不知道了..
    // 那个框架怎么设计呢?


}
