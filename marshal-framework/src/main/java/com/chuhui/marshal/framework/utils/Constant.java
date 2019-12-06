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


    enum REMOTE_FLAG {
        /**
         * 客户端第一次请求,请求服务端,以证明需要的服务是否存在
         */
        CLIENT_FIRST_REQUEST((short) 0);

        short value;

        REMOTE_FLAG(short value) {
            this.value = value;
        }

        public short getValue() {
            return value;
        }


        public static REMOTE_FLAG valueOf(short value) {
            REMOTE_FLAG[] values = REMOTE_FLAG.values();
            return Arrays.stream(values).filter(e -> e.value == value).findFirst().get();

        }

    }


}
