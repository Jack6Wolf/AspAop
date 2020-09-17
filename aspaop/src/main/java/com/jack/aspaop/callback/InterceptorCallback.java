package com.jack.aspaop.callback;

import android.support.annotation.NonNull;

import com.jack.aspaop.annotation.Interceptor;

/**
 * 拦截的回调
 *
 * @author jack
 * @see Interceptor
 * @since 2020/9/17 09:37
 */
public interface InterceptorCallback {
    /**
     * @param key        {@link Interceptor#value()}的值
     * @param methodName 拦截的method名字
     * @return 是否需要拦截
     */
    boolean intercept(@NonNull String key, String methodName);
}
