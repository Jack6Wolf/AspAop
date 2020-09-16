package com.jack.aopdemo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.jack.aspaop.AspAop;
import com.jack.aspaop.DemotionCallback;

import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author jack
 * @since 2020/9/14 15:03
 */
public class App extends Application {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        AspAop.init().setMemoryLevel(3);
        AspAop.init().addCallback(new DemotionCallback() {
            @Override
            public void demotion(MethodSignature signature, Object[] args) {
                Log.e("JACK", "demotion"+Thread.currentThread().getName());
            }
        });
    }
}
