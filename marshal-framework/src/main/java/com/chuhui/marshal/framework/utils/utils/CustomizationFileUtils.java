package com.chuhui.marshal.framework.utils.utils;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * CustomizationFileUtil
 *
 * 自定义的文件工具类,对第三方的工具类进行二次封装.
 *
 * @author: cyzi
 * @Date: 2019/11/13 0013
 * @Description:
 */
public class CustomizationFileUtils {
    final static private Logger logger = LoggerFactory.getLogger(CustomizationFileUtils.class);


    /**
     * 传递一个文件名,返回一个输入流
     * @param fileName 文件名
     * @return 输入流
     */
    public static InputStream openFileAsInputStream(String fileName) {

        try {
            return FileUtils.openInputStream(new File(fileName));
        } catch (IOException e) {
            logger.error("open file:{} as input stream failed,please check file.",fileName,e);
        }

        return null;
    }

}
