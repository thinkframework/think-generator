package io.github.thinkframework.generator.util;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest {
    @Test
    public void test1(){
        Assert.assertEquals("aBC",StringUtils.lowerCamel("a_b_c"));
        Assert.assertEquals("ABC",StringUtils.upperCamel("a_b_c"));
        Assert.assertEquals("",StringUtils.lowerCamel(""));
        Assert.assertEquals("",StringUtils.upperCamel(""));


        Assert.assertEquals("a_b_c",StringUtils.lowerUnderScore("ABC"));
    }
}
