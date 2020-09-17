package com.jack.aopdemo;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.jack.aspaop.AspAop;

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
        AspAop.init().setInterceptorCallback((key, methodName) -> {
            if (key.equals("needLogin")) {
                Toast.makeText(context, "请去登录！", Toast.LENGTH_SHORT).show();
                //拦截
                return true;
            }
            //放行
            return false;
        });
        AspAop.init().setDelayCallback((key, result, throwable) -> {
            Log.e("JACK", "delay:" + key + result + throwable);
        });
    }
}
