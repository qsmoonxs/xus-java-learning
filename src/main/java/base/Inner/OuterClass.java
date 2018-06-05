/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package base.Inner;

/**
 * 内部类
 *
 * @author xus
 * @since 2018-06-05 16:01
 *
 */
public class OuterClass {


    /** 嵌套类分为 静态（静态嵌套类） 和 非静态（内部类）**/
    private class InnerClass {

    }

    private static class StaticNestedClass {

    }

    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();

        /** 创建静态嵌套类
         *  静态内部类的作用：降低包的深度，方便类的使用，不依赖外部类 所以在new的时候不需要外部对象的引用**/
        OuterClass.StaticNestedClass staticNestedClass = new OuterClass.StaticNestedClass();

        /** 内部类
         *  内部类的作用：依赖外部类，可以自由使用外部类的所有方法和变量
         *
         */
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
    }

    /**  所以mybatis里要用嵌套类的话 必须是静态嵌套类 因为要不依赖外部类要new**/
}
