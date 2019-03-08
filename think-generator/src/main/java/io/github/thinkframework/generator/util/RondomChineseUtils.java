package io.github.thinkframework.generator.util;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * 01 - 09 区为特殊符号。
 * 16 - 55 区为一级汉字，按拼音排序。
 * 56 - 87 区为二级汉字，按部首/笔画排序。
 * 10 - 15 区及 88 - 94 区则未有编码。
 */
public class RondomChineseUtils {

    public static String byte2String(String s) throws RuntimeException {
        return byte2String(s,"GBK");
    }


    public static String byte2String(String s,String charsetName) throws RuntimeException {
        try {
            byte[] bytes = s.getBytes(charsetName);
            String result = "";
            for (int i = 0; i < bytes.length; i++) {
                String hex = Integer.toHexString(bytes[i] & 0xff);
                result += hex;
            }
            return result;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成一个中文字符串
     *
     * @param length
     * @return
     */
    public static String random(int length) throws RuntimeException {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(random());
        }
        return result.toString();
    }

    /**
     * 生成一个中文
     *
     * @return
     */
    public static String random() throws RuntimeException {
        try {
            Random random = new Random();
            Integer high, low;
            high = (0xb0 + Math.abs(random.nextInt(39)));//B0 + 0~39(16~55) 一级汉字所占区
            low = (0xa1 + Math.abs(random.nextInt(93)));//A1 + 0~93 每区有94个汉字

            byte[] result = new byte[]{high.byteValue(), low.byteValue()};

            return new String(result, "GBK");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
