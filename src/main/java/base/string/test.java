/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package base.string;

/**
 *
 *
 * @author xus
 * @since 2018-03-26 16:48
 *
 */
public class test {

    /**
     * StringBuffer类(或者StringBuilder 线程安全)和String一样，也用来代表字符串，
     * 只是由于StringBuffer的内部实现方式和String不同，所以StringBuffer在进行字符串处理时，不生成新的对象，在内存使用上要优于String类。
     */
    public static void main(String[] args) {
        /** 线程安全 stringbuilder不是线程安全的**/
        /** 初始容量是 初始化的字符串长度+16**/
        StringBuffer stringBuffer = new StringBuffer("xus");
        stringBuffer.append("yq");
        stringBuffer.deleteCharAt(4);
        stringBuffer.insert(4,"q");
        /** 将StringBuffer对象的中存储空间缩小到和字符串长度一样的长度，减少空间的浪费。 **/
        stringBuffer.trimToSize();
        System.out.println(stringBuffer);
        String s1 = stringBuffer.toString();

        StringBuffer s2 = new StringBuffer("x");
        s2.append("xxxxxxxxxxxxxxxxx");
        System.out.println(s2);

    }
}
