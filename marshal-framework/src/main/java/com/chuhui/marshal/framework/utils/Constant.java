package com.chuhui.marshal.framework.utils;

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
        CLIENT_FIRST_REQUEST(0);

        int value;

        REMOTE_FLAG(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


}
