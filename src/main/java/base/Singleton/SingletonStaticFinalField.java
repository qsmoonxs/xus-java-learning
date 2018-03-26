/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package base.Singleton;

/**
 * 饿汉式
 *
 * @author xus
 * @since 2018-01-15 16:31
 *
 */
public class SingletonStaticFinalField {

    /** 在类加载的时候就初始化了，但是这不是一种懒加载模式，不管有没有getInstace他都创建好了实例，如果创建实例需要依赖一些外部变量那么就不能用了**/
    private static final SingletonStaticFinalField instance = new SingletonStaticFinalField();

    private SingletonStaticFinalField(){}

    public static SingletonStaticFinalField getInstance()  {
        return instance;
    }
}

