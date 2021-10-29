package io.github.thinkframework.generator.core.internal.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理
 * 忽略字段
 * @author hdhxby
 * @since 2017/3/24
 */
public class IgnonreInvocationHandler implements InvocationHandler,Ignore {
    private Object object;

    private Boolean ignore;

    private IgnonreInvocationHandler() {

    }

    private IgnonreInvocationHandler(Object object, Boolean ignore) {
        setObject(object);
        setIgnore(ignore);
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public Boolean getIgnore() {
        return ignore;
    }

    public void setIgnore(Boolean ignore) {
        this.ignore = ignore == null ? false: ignore;
    }

    public static Object proxy(Object object, Boolean ignore) {
        Class[] interfaces = object.getClass().getInterfaces();
        Class[] proxyInterfaces = new Class[interfaces.length + 1];
        for (int i = 0; i < interfaces.length; i++) {
            proxyInterfaces[i] = interfaces[i];
        }
        proxyInterfaces[interfaces.length] = Ignore.class;
        return Proxy.newProxyInstance(IgnonreInvocationHandler.class.getClassLoader(),
            proxyInterfaces,
            new IgnonreInvocationHandler(object, ignore));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {
        if ("getIgnore".equals(method.getName())) {
            return getIgnore();
        } else {
            return method.invoke(object, args);
        }
    }
}

