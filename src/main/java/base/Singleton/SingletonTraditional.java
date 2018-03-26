/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package base.Singleton;

/**
 * 懒汉式 线程不安全
 *
 * @author xus
 * @since 2018-01-11 14:14
 *
 */
public class SingletonTraditional {

    private String name = "xus";
    private static SingletonTraditional instance;

    /** 因为单例模式 所以构造函数权限是私有的**/
    private SingletonTraditional() {
    }

    /** 多线程下会创建多个实例 **/
    public static SingletonTraditional getInstance() {
        if(instance == null) {
            instance = new SingletonTraditional();
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
