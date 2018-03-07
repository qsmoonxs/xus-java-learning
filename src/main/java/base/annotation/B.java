/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package base.annotation;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author xus
 * @since 2018-03-07 13:41
 *
 */
@A(name = "type", gid = Long.class)
public class B {
    @A(name = "param", id = 1, gid = Long.class)
    private Integer age;

    @A(name = "construct", id = 2, gid = Long.class)
    public B() {

    }

    @A(name = "public method", id = 3, gid = Long.class)
    public void a() {

    }

    @A(name="protected method", id=4, gid=Long.class) //类方法注解
    protected void b(){
        Map<String,String> m = new HashMap<>(0);
    }


    @A(name="private method", id=5, gid=Long.class) //类方法注解
    private void c(){
        Map<String,String> m = new HashMap<>(0);
    }

    public void b(Integer a){

    }
}
