/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package base.proxy;

/**
 *
 *
 * @author xus
 * @since 2018-03-26 15:36
 *
 */
public class OrderServiceImpl implements OrderService {

    @Override
    public void test1() {
        System.out.println("执行test1---------");
        test3();
    }

    @Override
    public void test2() {

        System.out.println("执行test2---------");
    }

    @Override
    public void test3() {
        System.out.println("执行test3---------");
    }
}
