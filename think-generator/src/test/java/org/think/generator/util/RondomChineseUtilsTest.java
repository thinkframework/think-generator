//package org.think.generator.util;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.junit.Test;
//
//import java.io.UnsupportedEncodingException;
//
//public class RondomChineseUtilsTest {
//    Log log = LogFactory.getLog(getClass());
//    @Test
//    public void tohex() throws UnsupportedEncodingException {
//
//        System.out.println(Integer.toHexString(10));
//        log.debug(RondomChineseUtils.byte2String("啊"));
//        System.out.println(RondomChineseUtils.byte2String("啊"));
//        System.out.println(RondomChineseUtils.byte2String("啊","UTF-8"));
//        System.out.println(RondomChineseUtils.byte2String("啊","Unicode"));
//
//    }
//
//    @Test
//    public void tostring(){
//        log.debug(RondomChineseUtils.byte2String("啊"));
//        System.out.println(RondomChineseUtils.random());
//        System.out.println(RondomChineseUtils.random(10));
//    }
//
//
//    @Test
//    public void excepition() throws UnsupportedEncodingException {
//        System.out.println(new String(new byte[]{(byte)0xd7,(byte)0xfa},"GBK"));
//        System.out.println(new String(new byte[]{(byte)0xd7,(byte)0xfb},"GBK"));
//        System.out.println(new String(new byte[]{(byte)0xd7,(byte)0xfc},"GBK"));
//        System.out.println(new String(new byte[]{(byte)0xd7,(byte)0xfd},"GBK"));
//        System.out.println(new String(new byte[]{(byte)0xd7,(byte)0xfe},"GBK"));
//
//    }
//}
