package com.chuhui.marshal.framework.utils.config;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.LinkedHashMap;

import static com.chuhui.marshal.framework.utils.FileUtils.openFileAsInputStream;

/**
 * MarshalConfig
 * 配置模型
 *
 * @author: cyzi
 * @Date: 2019/11/13 0013
 * @Description:TODO
 */
@Getter
@Setter
public class MarshalConfig {
    final static private Logger logger = LoggerFactory.getLogger(MarshalConfig.class);

    private MarshalBasicConfig marshalBasic;
    /**
     * 服务工厂
     */
    private String serverFactory;


    public static MarshalConfig readConfigFromFile(String configFileName) {
        InputStream fileStream = openFileAsInputStream(configFileName);
        Yaml yaml = new Yaml();
        MarshalConfig config = null;

        try {
            config = yaml.loadAs(fileStream, MarshalConfig.class);

        } catch (Exception e) {
            logger.error("resolved config file:{} failed,please check file and class:{} ", configFileName, MarshalConfig.class.getName(), e);
        }

        return config;
    }


    @Getter
    @Setter
    public static class MarshalBasicConfig {
        /**
         * 独立模式
         */
        private Boolean standalone;
        /**
         * 服务端的端口
         */
        private Integer serverPort;



    }


}
