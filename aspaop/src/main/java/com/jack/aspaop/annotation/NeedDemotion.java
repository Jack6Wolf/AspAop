package com.jack.aspaop.annotation;


import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 是否降级注解
 * 使用该注解标记method，该手机内存满足条件，则不执行后续逻辑
 *
 * @author jack
 * @since 2020/9/11 18:00
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@IntDef(value = {NeedDemotion.DEFAULT, NeedDemotion.LEVEL_1, NeedDemotion.LEVEL_2, NeedDemotion.LEVEL_3, NeedDemotion.LEVEL_4, NeedDemotion.LEVEL_5, NeedDemotion.LEVEL_6})
public @interface NeedDemotion {
    /**
     * 手机内存划分的内存等级
     */
    int DEFAULT = 10;//不降级
    int LEVEL_1 = 1;//512M
    int LEVEL_2 = 2;//1G
    int LEVEL_3 = 3;//2G
    int LEVEL_4 = 4;//3G
    int LEVEL_5 = 5;//4G
    int LEVEL_6 = 6;//>4G

    /**
     * 当前需要降级的等级
     */
    int value() default DEFAULT;

}
