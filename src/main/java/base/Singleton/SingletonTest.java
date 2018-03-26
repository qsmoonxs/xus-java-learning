/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package base.Singleton;

/**
 * 单例模式保证类仅有一个实例并提供访问他的全局访问点
 *
 * @author xus
 * @since 2018-01-11 16:02
 *
 */
public class SingletonTest {

    public static void  main(String[] args) {
        SingletonTraditional singleton = SingletonTraditional.getInstance();
        System.out.println(singleton.getName());
    }

}
