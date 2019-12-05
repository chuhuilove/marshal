package com.chuhui.marshal.framework.utils;

import com.chuhui.marshal.framework.exception.MarshalNullPointException;

/**
 * CheckNullUtils
 *
 * @author: cyzi
 * @Date: 2019/12/2 0002
 * @Description:TODO
 */
public class DataCheckUtils {


    /**
     * 判断给定的对象是否为空,如果为空,则 抛出异常
     *
     * @param object 需要校验的对象
     * @param name   对象名
     * @param <T>    泛型对象
     * @return 如果不为空, 则返回原始对象
     */
    public static <T> T assertNotNull(final T object, String name) {

        if (object == null) {
            throw new MarshalNullPointException(name + " must not null");
        }
        return object;
    }
}
