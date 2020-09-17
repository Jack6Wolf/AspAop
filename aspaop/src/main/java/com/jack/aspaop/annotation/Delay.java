package com.jack.aspaop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 延迟任务，默认不延迟
 * 请在主线程使用该注解
 *
 * @author jack
 * @since 2020/9/17 09:36
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Delay {
    /**
     * 延迟任务的key，为了方便确定延迟任务的执行情况
     */
    String key() default "";

    /**
     * 延迟的时间 单位：ms
     */
    int delay() default 0;
}
