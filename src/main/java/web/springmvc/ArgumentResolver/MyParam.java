/**
 * Copyright(C) 2017 Hangzhou Differsoft Co., Ltd. All rights reserved.
 */
package web.springmvc.ArgumentResolver;

import java.lang.annotation.*;

/**
 *
 *
 * @author xus
 * @since 2018-03-26 13:30
 *
 */
@Documented
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyParam {
}
