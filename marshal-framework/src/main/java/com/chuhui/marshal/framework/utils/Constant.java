package com.chuhui.marshal.framework.utils;

import java.util.Arrays;

/**
 * 静态常量
 *
 * @author: cyzi
 * @Date: 2019/11/13 0013
 * @Description:TODO
 */
public interface Constant {


    Integer MAX_ANNOTATED_COUNT = 1;


    String REQUEST_MAPPING_PATH_METHOD_NAME = "path";
    String REQUEST_MAPPING_VALUE_METHOD_NAME = "value";
    String SPRING_FRAMEWORK_CONTROLLER_PREFIX = "org.springframework.";

    String URL_DELIMITER = "/";


    enum CLIENT_REMOTE_REQUEST_FLAG {
        /**
         * 消费者第一次请求,请求服务端,以证明需要的服务是否存在
         */
        CONSUMER_FIRST_REQUEST((short) 0),
        /**
         * 生产者第一次请求,请求服务端,将服务推送到marshal服务上
         */
        PRODUCER_FIRST_REQUEST((short) 1);

        short value;

        CLIENT_REMOTE_REQUEST_FLAG(short value) {
            this.value = value;
        }

        public short getValue() {
            return value;
        }


        public static CLIENT_REMOTE_REQUEST_FLAG valueOf(short value) {
            CLIENT_REMOTE_REQUEST_FLAG[] values = CLIENT_REMOTE_REQUEST_FLAG.values();
            return Arrays.stream(values).filter(e -> e.value == value).findFirst().get();
        }
    }

    enum SERVER_REMOTE_RESPONSE_FLAG {

        /**
         * 消费者第一次请求的响应
         */
        CONSUMER_FIRST_RESPONSE((short) 0),
        /**
         * 生产者第一次请求的先响应
         */
        PRODUCER_FIRST_RESPONSE((short) 1);

        short value;

        SERVER_REMOTE_RESPONSE_FLAG(short value) {
            this.value = value;
        }

        public short getValue() {
            return value;
        }


        public static SERVER_REMOTE_RESPONSE_FLAG valueOf(short value) {
            SERVER_REMOTE_RESPONSE_FLAG[] values = SERVER_REMOTE_RESPONSE_FLAG.values();
            return Arrays.stream(values).filter(e -> e.value == value).findFirst().get();
        }

    }

    enum RESPONSE_FLAG {
        /**
         * 客户端的请求在服务端执行成功
         */
        SUCCESS((byte) 0),
        /**
         * 客户端的请求在服务端执行失败
         */
        FAILED((byte) 1);

        byte value;

        RESPONSE_FLAG(byte value) {
            this.value = value;
        }

        public short getValue() {
            return value;
        }


        public static RESPONSE_FLAG valueOf(byte value) {
            RESPONSE_FLAG[] values = RESPONSE_FLAG.values();
            return Arrays.stream(values).filter(e -> e.value == value).findFirst().get();
        }

    }


}
