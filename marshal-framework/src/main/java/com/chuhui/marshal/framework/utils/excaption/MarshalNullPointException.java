package com.chuhui.marshal.framework.utils.excaption;

/**
 * MarshalNullPointException
 * 自己封装的空指针异常
 *
 * @author: cyzi
 * @Date: 2019/12/2 0002
 * @Description:TODO
 */
public class MarshalNullPointException extends NullPointerException {


    public MarshalNullPointException(String s) {
        super(s);
    }
}
