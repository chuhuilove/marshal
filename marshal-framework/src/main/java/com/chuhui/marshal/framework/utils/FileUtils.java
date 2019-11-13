package com.chuhui.marshal.framework.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * FileUtils
 *
 * @author: cyzi
 * @Date: 2019/11/13 0013
 * @Description:TODO
 */
public class FileUtils {

    // 假日知

    /**
     * 传递一个文件名,返回一个输入流
     * @param fileName 文件名
     * @return 输入流
     */
    public static InputStream openFileAsInputStream(String fileName) {

        try {
            return org.apache.commons.io.FileUtils.openInputStream(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
