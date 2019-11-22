package io.github.thinkframework.common.constant;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class IDTest {
    @Test
    public void test(){
        Assert.assertTrue(new ID("130426198901163212").validate());
    }
}
