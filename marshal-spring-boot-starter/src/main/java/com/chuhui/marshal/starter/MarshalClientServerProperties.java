package com.chuhui.marshal.starter;

import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * MarshalClientConfiguration
 *
 * @author: cyzi
 * @Date: 2019/12/5 0005
 * @Description:TODO
 */
@EnableConfigurationProperties
public class MarshalClientServerProperties {

    private String value;
    private String[] requireProducer;
    private String[] marshalServer;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String[] getRequireProducer() {
        return requireProducer;
    }

    public void setRequireProducer(String[] requireProducer) {
        this.requireProducer = requireProducer;
    }

    public String[] getMarshalServer() {
        return marshalServer;
    }

    public void setMarshalServer(String[] marshalServer) {
        this.marshalServer = marshalServer;
    }
}
