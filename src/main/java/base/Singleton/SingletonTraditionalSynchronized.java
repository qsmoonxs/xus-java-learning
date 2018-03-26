/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package base.Singleton;

/**
 * 懒汉式 线程安全
 *
 * @author xus
 * @since 2018-01-11 16:48
 *
 */
public class SingletonTraditionalSynchronized {

    private String name = "xus";
    private static SingletonTraditionalSynchronized instance;

    private SingletonTraditionalSynchronized() {
    }

    /** 虽然做到了线程安全，并且解决了多实例的问题，
     *  但是它并不高效。因为在任何时候只能有一个线程调用 getInstance() 方法
     *  但是同步操作只需要在第一次调用时才被需要，即第一次创建单例实例对象时。
     **/
    public static synchronized  SingletonTraditionalSynchronized getInstance() {
        if(instance == null) {
            instance = new SingletonTraditionalSynchronized();
        }
        return instance;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
