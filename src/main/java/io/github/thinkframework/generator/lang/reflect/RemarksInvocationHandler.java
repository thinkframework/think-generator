package io.github.thinkframework.generator.lang.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理模式
 * 有些类不适合添加remarks方法
 * 通过代理实现
 *
 * @author lixiaobin
 * @since 2017/3/24
 */
public class RemarksInvocationHandler<T> implements InvocationHandler {
    private Object object;
    private String remarks;

    public RemarksInvocationHandler() {

    }

    public RemarksInvocationHandler(Object object, String remarks) {
        setObject(object);
        setRemarks(remarks);
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    public static Object proxy(Object object, String description) {
        Class[] interfaces = object.getClass().getInterfaces();
        Class[] proxyInterfaces = new Class[interfaces.length + 1];
        for (int i = 0; i < interfaces.length; i++) {
            proxyInterfaces[i] = interfaces[i];
        }
        proxyInterfaces[interfaces.length] = Remarks.class;
        return Proxy.newProxyInstance(RemarksInvocationHandler.class.getClassLoader(),
            proxyInterfaces,
            new RemarksInvocationHandler(object, description));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {
        if ("getRemarks".equals(method.getName())) {
            return getRemarks();
        } else {
            return method.invoke(object, args);
        }
    }
}

