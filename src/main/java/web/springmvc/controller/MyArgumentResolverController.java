/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package web.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import web.springmvc.ArgumentResolver.MyParam;

/**
 *
 *
 * @author xus
 * @since 2018-03-26 13:36
 *
 */
@Controller
public class MyArgumentResolverController {

    /**
     * 一开始这个自定义注解没起作用，经过排查 发现是<mvc:annotation-driven>没起作用
     * 猜测原因是xml里已经有写好的适配器和映射器 把那些注释掉 只剩下mvc标签和视图解析器就可以了
     * @param value
     */
    @RequestMapping(value = "/argument/resolver/test")
    public void testMyArgumentResolver(@MyParam String value) {
        System.out.println(value);
    }
}
