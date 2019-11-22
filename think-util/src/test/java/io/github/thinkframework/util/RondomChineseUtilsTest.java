package io.github.thinkframework.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Ignore;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

@Slf4j
@Ignore
public class RondomChineseUtilsTest {
    @Test
    public void tohex() throws UnsupportedEncodingException {

        log.debug(Integer.toHexString(10));
        log.debug(RondomChineseUtils.byte2String("啊"));
        log.debug(RondomChineseUtils.byte2String("啊"));
        log.debug(RondomChineseUtils.byte2String("啊","UTF-8"));
        log.debug(RondomChineseUtils.byte2String("啊","Unicode"));

    }

    @Test
    public void tostring(){
        log.debug(RondomChineseUtils.byte2String("啊"));
        log.debug(RondomChineseUtils.random());
        log.debug(RondomChineseUtils.random(10));
    }


    @Test
    public void excepition() throws UnsupportedEncodingException {
        log.debug(new String(new byte[]{(byte)0xd7,(byte)0xfa},"GBK"));
        log.debug(new String(new byte[]{(byte)0xd7,(byte)0xfb},"GBK"));
        log.debug(new String(new byte[]{(byte)0xd7,(byte)0xfc},"GBK"));
        log.debug(new String(new byte[]{(byte)0xd7,(byte)0xfd},"GBK"));
        log.debug(new String(new byte[]{(byte)0xd7,(byte)0xfe},"GBK"));

    }
}
