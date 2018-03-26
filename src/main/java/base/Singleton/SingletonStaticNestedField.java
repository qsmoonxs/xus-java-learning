/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package base.Singleton;

/**
 * 最简单推荐的方法
 *
 * @author xus
 * @since 2018-01-15 16:34
 *
 */
public class SingletonStaticNestedField {

    /** 只有用到这个内部类的时候会去加载类**/
    private static class SingletonHolder {
        private static final SingletonStaticNestedField instace = new SingletonStaticNestedField();
    }
    private SingletonStaticNestedField(){}

    /** final方法 禁止重写**/
    public static final SingletonStaticNestedField getInstance() {
        return SingletonHolder.instace;
    }
}
