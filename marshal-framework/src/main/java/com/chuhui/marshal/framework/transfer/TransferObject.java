package com.chuhui.marshal.framework.transfer;

import com.chuhui.marshal.framework.utils.Constant.REMOTE_FLAG;
import lombok.Getter;
import lombok.Setter;

/**
 * TransferObject
 *
 * @author: cyzi
 * @Date: 2019/12/6 0006
 * @Description:TODO
 */
@Setter
@Getter
public class TransferObject {

    private String requestHeader;
    private Integer sumLength;
    private REMOTE_FLAG flag;
    private byte[] dataBody;



}
