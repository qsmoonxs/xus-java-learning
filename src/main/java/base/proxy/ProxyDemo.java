/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package base.proxy;

import web.springmvc.model.Orders;

import java.lang.reflect.Field;

/**
 *
 *
 * @author xus
 * @since 2018-03-26 15:49
 *
 */
public class ProxyDemo {

    public static void main(String[] args)  throws NoSuchFieldException, IllegalAccessException {
        OrderService orderService = new OrderServiceImpl();
        OrderProxy orderProxy = new OrderProxy(orderService);
        orderService = (OrderService) orderProxy.getProxy();
        /**
         * 在OrderServiceImpl 类中由于test1(）没有调用test2（），他们方法的执行都是使用了代理的，
         * 也就是说test1和test2都是通过代理对象调用的invoke（）方法
         * 这是因为在Java中test1（）中调用test2（）中的方法，本质上就相当于把test2（）的方法体放入到test1（）中，也就是内部方法，
         * 同样的不管你嵌套了多少层，只有代理对象proxy 直接调用的那一个方法才是真正的走代理的
         */
        orderService.test1();
       // orderService.test2();
    }
}
