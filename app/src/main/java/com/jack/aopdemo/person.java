package com.jack.aopdemo;

import com.jack.aspaop.annotation.DurationLog;
import com.jack.aspaop.annotation.Interceptor;

/**
 * @author jack
 * @since 2020/9/17 13:16
 */
class person {
    int age;

    @DurationLog
    public person(int age) {
        this.age = age;
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Interceptor("needLogin")
    @DurationLog
    public person() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
