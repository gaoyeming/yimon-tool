package org.yimon.tool.util;


import org.yimon.tool.exception.InvokeException;
import org.yimon.tool.exception.ValidateException;
import org.yimon.tool.result.ReturnCode;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author yeming.gao
 * @Description: 雪花算法实现
 * <p>
 * SnowFlake算法用来生成64位的ID，刚好可以用long整型存储，能够用于分布式系统中生产唯一的ID，
 * 并且生成的ID有大致的顺序。 在这次实现中，生成的64位ID可以分成5个部分：
 * 0 - 41位时间戳 - 5位数据中心标识 - 5位机器标识 - 12位序列号
 * @date 2020/07/28 16:15
 */
public final class SnowFlakeUtils {
    /**
     * 序列号占用的位数
     */
    private static final long SEQUENCE_BIT = 12;
    /**
     * 序列号最大值
     */
    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);
    /**
     * 每一部分向左的位移
     */
    private static final long MACHINE_LEFT = SEQUENCE_BIT;
    /**
     * 起始的时间戳
     */
    private static final long START_STMP = 1480166465631L;
    /**
     * 序列号
     */
    private static long sequence = 0L;
    /**
     * 上一次时间戳
     */
    private static long lastStmp = -1L;

    private SnowFlakeUtils() {
    }

    /**
     * 产生下一个ID
     *
     * @return String
     */
    public static synchronized String generateTraceId(String prefix) {
        long currStamp = System.currentTimeMillis();
        if (currStamp < lastStmp) {
            throw new InvokeException(ReturnCode.EXCEPTION.code(), "Clock moved backwards. Refusing to generate id");
        }
        if (currStamp == lastStmp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStamp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }
        lastStmp = currStamp;
        return prefix + (parseString16ToLong(getCurrentIpLocalMac()) << MACHINE_LEFT //机器标识部分
                | sequence) + (currStamp - START_STMP); //时间戳部分; //序列号部分
    }

    private static long getNextMill() {
        long mill = System.currentTimeMillis();
        while (mill <= lastStmp) {
            mill = System.currentTimeMillis();
        }
        return mill;
    }

    /**
     * 获取当前所用ip的mac地址
     *
     * @return String
     */
    private static String getCurrentIpLocalMac() {
        byte[] mac;
        try {
            InetAddress ia = InetAddress.getLocalHost();
            mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
        } catch (SocketException | UnknownHostException e) {
            throw new InvokeException(ReturnCode.EXCEPTION.code(), "get local mac adress exception");
        }

        StringBuilder sb = new StringBuilder();
        for (byte b : mac) {
            //字节转换为整数
            int temp = b & 0xff;
            // 把无符号整数参数所表示的值转换成以十六进制表示的字符串
            String str = Integer.toHexString(temp).toUpperCase();
            if (str.length() == 1) {
                sb.append("0").append(str);
            } else {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    /**
     * 将16进制的字符串转化为long值
     *
     * @return String
     */
    private static long parseString16ToLong(String str16) {
        if (str16 == null) {
            throw new ValidateException(ReturnCode.FAILURE.code(), "null");
        }
        //先转化为小写
        str16 = str16.toLowerCase();
        //如果字符串以0x开头，去掉0x
        str16 = str16.startsWith("0x") ? str16.substring(2) : str16;
        if (str16.length() > 16) {
            throw new ValidateException(ReturnCode.FAILURE.code(), "For input string '" + str16 + "' is to long");
        }
        byte[] bA = str16.getBytes();
        long re = 0L;
        for (byte value : bA) {
            //加下一位的字符时，先将前面字符计算的结果左移4位
            re <<= 4;
            //0-9数组
            byte b = (byte) (value - 48);
            //A-F字母
            if (b > 9) {
                b = (byte) (b - 39);
            }
            //非16进制的字符
            if (b > 15 || b < 0) {
                throw new ValidateException(ReturnCode.FAILURE.code(), "For input string '" + str16);
            }
            re += b;
        }
        return re;
    }
}
