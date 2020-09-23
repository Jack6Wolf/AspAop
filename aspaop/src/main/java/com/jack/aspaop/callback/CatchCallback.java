package com.jack.aspaop.callback;

import android.support.annotation.NonNull;

/**
 * 异常捕获回调
 *
 * @author jack
 * @since 2020/9/23 14:45
 */
public interface CatchCallback {
    /**
     * @param key       {@link com.jack.aspaop.annotation.Catch#value()}的值
     * @param throwable 捕获的异常信息
     * @return 是否需要捕获
     */
    boolean capture(@NonNull String key, Throwable throwable);
}
