package com.chuhui.marshal.framework.utils.config;

import lombok.Getter;
import lombok.Setter;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

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


    private Boolean standalone;
    private Integer serverPort;



    public MarshalConfig(String configFileName){

        InputStream fileStream = openFileAsInputStream(configFileName);
        Yaml yaml=new Yaml();
        MarshalConfig config=  yaml.load(fileStream);
    }





}
