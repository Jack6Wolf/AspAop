package com.jack.aspaop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法拦截，通过回调返回拦截处理
 *
 * @author jack
 * @since 2020/9/17 09:27
 */
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface Interceptor {
    /**
     * 拦截标识（key）
     */
    String value() default "";
}
