package com.jack.aspaop;

import android.os.Handler;
import android.os.Looper;

import com.jack.aspaop.annotation.NeedDemotion;
import com.jack.aspaop.callback.DelayCallback;
import com.jack.aspaop.callback.DemotionCallback;
import com.jack.aspaop.callback.InterceptorCallback;


/**
 * Sdk初始化
 * 设置手机内存等级
 * 设置拦截方法后的回调
 *
 * @author jack
 * @since 2020/9/14 17:49
 */
public class AspAop {
    public static final int DEBUG = 1;
    public Handler handler;
    private DemotionCallback demotionCallback;
    private InterceptorCallback interceptorCallback;
    private DelayCallback delayCallback;
    private int level = NeedDemotion.DEFAULT;
    private int buildType = DEBUG;


    private AspAop() {
        handler = new Handler(Looper.getMainLooper());
    }

    public static AspAop init() {
        return SingletonInstance.INSTANCE;
    }

    public DelayCallback getDelayCallback() {
        return delayCallback;
    }

    public void setDelayCallback(DelayCallback delayCallback) {
        this.delayCallback = delayCallback;
    }

    public int getBuildType() {
        return buildType;
    }

    public void setBuildType(int buildType) {
        this.buildType = buildType;
    }

    public DemotionCallback getDemotionCallback() {
        return demotionCallback;
    }

    public void setDemotionCallback(DemotionCallback callback) {
        this.demotionCallback = callback;
    }

    public int getMemoryLevel() {
        return level;
    }

    public void setMemoryLevel(int level) {
        this.level = level;
    }

    public InterceptorCallback getInterceptorCallback() {
        return interceptorCallback;
    }

    public void setInterceptorCallback(InterceptorCallback interceptorCallback) {
        this.interceptorCallback = interceptorCallback;
    }

    private static class SingletonInstance {
        private static AspAop INSTANCE = new AspAop();
    }
}
