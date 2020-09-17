package com.jack.aspaop.annotation;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 防止Click事件重复
 *
 * @author jack
 * @since 2020/9/17 10:22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SingleClick {
    /**
     * 需要防止重复的控件id
     */
    int[] ids() default {View.NO_ID};

    /**
     * 前后点击检测周期，默认500ms
     */
    int value() default 500;
}
