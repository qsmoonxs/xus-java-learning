/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 *一个就是标记源码，对源码做说明，编译的时候就没了，一个是编译的时候用的，对编译器做一些优化，编译完就没了
 * 一般用运行时不然反射就拿不到了
 * @author xus
 * @since 2018-03-07 13:37
 *
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface A {
    String name();
    /** 默认是0 **/
    int id() default 0;
    Class<Long> gid();
}
