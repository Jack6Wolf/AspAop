package com.jack.aopdemo;

import android.util.Log;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author jack
 * @since 2020/9/23 13:47
 */
@Aspect
public class ThrowAspect {
    private static final String TAG = "ThrowAspect";

    @AfterThrowing(value = "execution(* com.jack.aopdemo.MainActivity.AfterThrowing*(..))", throwing = "exception")
    public void testAspectAfterReturning(Exception exception) {
        Log.e(TAG, "AfterThrowing-exception:" + exception.getMessage());
    }
}
