//package org.think.generator.lang.reflect;
//
//import org.junit.Test;
//import Clazz;
//import org.think.generator.lang.ClazzImpl;
//
//import java.lang.reflect.InvocationTargetException;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
//
///**
// *
// * @author lixiaobin
// * @since 2017/3/24
// */
//public class Re {
//    @Test
//    public void test(){
//        String description = "description";
//        ClazzImpl clazz = new ClazzImpl();
//        Clazz proxy = (Clazz) RemarksInvocationHandler.proxy(clazz,"description");
//        try {
//            assertEquals("description",proxy.getClass().getMethod("getRemarks").invoke(proxy));
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//            fail();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//            fail();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//            fail();
//        }
//    }
//}
