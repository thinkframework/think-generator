package org.think.generator;

import org.junit.Test;

import java.util.Map;
import java.util.Properties;

/**
 * @author lixiaobin
 * @since 2017/5/13.
 */
public class EnvTest {
    @Test
    public void test(){

        System.out.println("properties:");

        for(Map.Entry<Object,Object> entry : System.getProperties().entrySet()){
            System.out.println(entry.getKey()+"\t"+entry.getValue());
        }

        System.out.println("env:");

       for(Map.Entry<String,String> entry : System.getenv().entrySet()){
            System.out.println(entry.getKey()+"\t"+entry.getValue());
        }
    }
}
