/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package base.Singleton;

/**
 * 双重检查锁
 *
 * @author xus
 * @since 2018-01-11 17:23
 *
 */
public class SingletonDoubleChecked {

    private String name = "xus";
    private static SingletonDoubleChecked instance;

    private SingletonDoubleChecked() {

    }

    public static SingletonDoubleChecked getInstance() {
        /** 当线程1 执行了3 还没执行2的时候 线程2走到了这里 判断instance非null 但是其实还没初始化，线程2就返回了instance 这就有问题**/
        /** 解决办法，在instance变量加上volatile 取操作必须在执行完123或者132之后才可以，那这样就没问题了**/
        if(instance == null) {
            synchronized (SingletonDoubleChecked.class) {
                if(instance == null) {
                    /** 这里不是原子操作
                     * 1. 给 instance 分配内存
                     * 2. 调用 Singleton 的构造函数来初始化成员变量
                     * 3. 将instance对象指向分配的内存空间（执行完这步 instance 就为非 null 了）
                     * 执行顺序可能是123 也有可能132
                     * **/
                    instance = new SingletonDoubleChecked();
                }
            }
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
