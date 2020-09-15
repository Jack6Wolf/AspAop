package com.jack.aopdemo;

import android.app.Application;
import android.util.Log;

import com.jack.aspaop.AspAop;
import com.jack.aspaop.DemotionCallback;

import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author jack
 * @since 2020/9/14 15:03
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AspAop.init().setMemoryLevel(3);
        AspAop.init().addCallback(new DemotionCallback() {
            @Override
            public void demotion(MethodSignature signature) {
                Log.e("JACK", "demotion");
            }
        });
    }
}
