package com.chuhui.marshal.framework.utils;

import com.chuhui.marshal.framework.transfer.TransferObject;
import io.netty.buffer.ByteBuf;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.chuhui.marshal.framework.utils.Constant.CLIENT_REMOTE_REQUEST_FLAG;
import static com.chuhui.marshal.framework.utils.ServerFactoryUtils.*;

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
            transferObject.setFlag(CLIENT_REMOTE_REQUEST_FLAG.valueOf(requestFlag));
            return transferObject;
        }
        throw new RuntimeException("parse request body error,包不完整,解析请求头失败");
    }

    private static void checkIntegrity(int bodyLength, int sumLength) {
        if (bodyLength != sumLength - HEADER_LENGTH - INTEGER_LENGTH - SHORT_LENGTH) {
            throw new RuntimeException("parse request body error,包不完整");
        }
    }


    /**
     * 数组去重,不能传入基本类型数组
     * <p>
     * 如果传入的数组为{@code null}或者{@code originalArray.length}为0,则直接返回原始空元素.
     * 利用{@link Set}的去重机制,将原始数组转化为一个{@code Set}集合,
     * 转化成{@code Set}集合后,如果当前set中的元素数量和原始数组中的长度一致,
     * 则认为原始数组没有重复元素的存在,直接返回原始数组.
     * <p>
     * 由于传入的数组是一个泛型类,{@code T[] res=new T[]}这行代码编译不通过,所以,只能选择找出泛型数组的原始类.
     * 然后通过{@code Array}进行反射,获取一个新的泛型数组.
     *
     * @param originalArray 传入的需要去重的数组
     * @param <T>           泛型数组
     * @return 返回去重后的新数组
     */

    public static <T> T[] arrayDeduplication(T[] originalArray) {
        if (ArrayUtils.isEmpty(originalArray)) {
            return originalArray;
        }
        Set<T> sets = new HashSet<>(Arrays.asList(originalArray));
        if (sets.size() == originalArray.length) {
            return originalArray;
        }

        /**
         * 获取到泛型数组的原始类型 比如:String,Long等非基本类型
         */
        Class<?> aClass = originalArray.getClass().getComponentType();
        @SuppressWarnings("unchecked")
        T[] result = (T[]) Array.newInstance(aClass, sets.size());
        return sets.toArray(result);
    }

    /**
     * 基本类型,{@code int}型去重
     * @param originalArray 原始数组.
     * @return 去重后的数组
     */
    public static int[] arrayDeduplication(int[] originalArray) {
        if (ArrayUtils.isEmpty(originalArray)) {
            return originalArray;
        }
        return ArrayUtils.toPrimitive(arrayDeduplication(ArrayUtils.toObject(originalArray)));
    }

}
