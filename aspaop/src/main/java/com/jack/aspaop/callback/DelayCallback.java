package com.jack.aspaop.callback;

import android.support.annotation.Nullable;

import com.jack.aspaop.annotation.Delay;

/**
 * 延迟任务执行情况
 *
 * @author jack
 * @since 2020/9/17 14:27
 */
public interface DelayCallback {
    /**
     * @param key       {@link Delay#key()}
     * @param result    任务返回值
     * @param throwable 如果期间发生异常，无异常说明成功执行
     */
    void taskResult(String key, @Nullable Object result, @Nullable Throwable throwable);
}
