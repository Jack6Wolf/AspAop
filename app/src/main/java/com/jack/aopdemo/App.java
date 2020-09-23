package com.jack.aopdemo;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.jack.aspaop.AspAop;
import com.jack.aspaop.callback.CatchCallback;
import com.jack.aspaop.callback.DelayCallback;
import com.jack.aspaop.callback.InterceptorCallback;

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
        AspAop.init().setDemotionCallback((signature, args) -> Log.e("JACK", "demotion" + Thread.currentThread().getName()));
        AspAop.init().setInterceptorCallback(new InterceptorCallback() {
            @Override
            public boolean intercept(@NonNull String key, String methodName) {
                if (key.equals("needLogin")) {
                    Toast.makeText(context, "请去登录！", Toast.LENGTH_SHORT).show();
                    //拦截
                    return true;
                }
                //放行
                return false;
            }
        });
        AspAop.init().setDelayCallback(new DelayCallback() {
            @Override
            public void taskResult(String key, @Nullable Object result, @Nullable Throwable throwable) {
                Log.e("JACK", "delay:" + key + result + throwable);
            }
        });

        AspAop.init().setCatchCallback(new CatchCallback() {
            @Override
            public boolean capture(@NonNull String key, Throwable throwable) {
                if (key.equals("click")) {
                    Log.e("JACK", "capture:" + throwable.getMessage());
                    return true;
                }
                return false;
            }
        });
    }
}
