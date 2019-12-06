package com.chuhui.marshal.framework.utils;

import com.chuhui.marshal.framework.transfer.TransferObject;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

import static com.chuhui.marshal.framework.utils.ServerFactoryUtils.HEADER_LENGTH;
import static com.chuhui.marshal.framework.utils.ServerFactoryUtils.TRANSFER_HEADER;
import static com.chuhui.marshal.framework.utils.ServerFactoryUtils.INTEGER_LENGTH;
import static com.chuhui.marshal.framework.utils.ServerFactoryUtils.SHORT_LENGTH;
import static com.chuhui.marshal.framework.utils.Constant.REMOTE_FLAG;

/**
 * CheckNullUtils
 *
 * @author: cyzi
 * @Date: 2019/12/2 0002
 * @Description:TODO
 */
public class DataCheckUtils {


    public static TransferObject parseByteBuf(ByteBuf byteBuf) {
        String header = byteBuf.readBytes(HEADER_LENGTH).toString(StandardCharsets.UTF_8);
        int sumLength = byteBuf.readInt();
        short requestFlag = byteBuf.readShort();
        byte[] dataBody = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(dataBody);

        checkIntegrity(dataBody.length, sumLength);

        if (header.equals(TRANSFER_HEADER)) {
            TransferObject transferObject = new TransferObject();

            transferObject.setDataBody(dataBody);
            transferObject.setSumLength(sumLength);
            transferObject.setRequestHeader(header);
            transferObject.setFlag(REMOTE_FLAG.valueOf(requestFlag));
            return transferObject;
        }
        throw new RuntimeException("parse request body error,包不完整,解析请求头失败");
    }

    private static void checkIntegrity(int bodyLength, int sumLength) {
        if (bodyLength != sumLength - HEADER_LENGTH - INTEGER_LENGTH - SHORT_LENGTH) {
            throw new RuntimeException("parse request body error,包不完整");
        }
    }

}
