/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package base.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 *
 * @author xus
 * @since 2018-03-26 15:46
 *
 */
public class OrderProxy implements InvocationHandler {

    private Object target;

    public OrderProxy() {
    }

    public OrderProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //我们使用这个标志来识别是否使用代理还是使用方法本体
        if(method.getName().startsWith("test")) {
            System.out.println("======分隔符======");
        }
        return method.invoke(target,args);
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(), this);
    }
}
