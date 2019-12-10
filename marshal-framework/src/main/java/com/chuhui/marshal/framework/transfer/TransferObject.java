package com.chuhui.marshal.framework.transfer;

import com.chuhui.marshal.framework.utils.Constant.CLIENT_REMOTE_REQUEST_FLAG;
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
    private CLIENT_REMOTE_REQUEST_FLAG flag;
    private byte[] dataBody;

}
