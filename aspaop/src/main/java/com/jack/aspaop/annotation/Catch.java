package com.jack.aspaop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 提供try-catch安全机制
 *
 * @author jack
 * @since 2020/9/23 14:34
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Catch {
    /**
     * catch之后回调标识
     */
    String value() default "";
}
