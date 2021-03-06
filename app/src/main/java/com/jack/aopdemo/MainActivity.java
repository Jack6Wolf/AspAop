package com.jack.aopdemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jack.aspaop.annotation.Catch;
import com.jack.aspaop.annotation.DurationLog;
import com.jack.aspaop.annotation.Interceptor;
import com.jack.aspaop.annotation.NeedDemotion;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @SuppressLint("SupportAnnotationUsage")
    @NeedDemotion(NeedDemotion.LEVEL_6)
    private boolean test(int i, boolean flag) {
        boolean is = true;
        Toast.makeText(App.getContext(), "点击了我", Toast.LENGTH_SHORT).show();
        is = false;
        return is;
    }

    @DurationLog
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test(1);
        test(1, 2);
    }

    public void click(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean test = test(1, false);
                Log.e("JACK", test + Thread.currentThread().getName());
            }
        }).start();

    }

    public void click1(View view) {
        test1();
    }

    private boolean test1() {
        new person();
        return false;
    }

    public void click2(View view) {
        int level6 = NeedDemotion.LEVEL_6;
        Log.e("JACK", level6 + "");
        Reflection.setStaticFieldValue(NeedDemotion.class, "LEVEL_6", 1);
        int level61 = NeedDemotion.LEVEL_6;
        Log.e("JACK", level61 + "");
    }

    @Interceptor
    public void click3(View view) {
        startActivity(new Intent(this, MainActivity2.class));
    }

    @DurationLog
    public void test(int i) {
        int level6 = NeedDemotion.LEVEL_6;
        Log.e("JACK", level6 + "");
        Reflection.setStaticFieldValue(NeedDemotion.class, "LEVEL_6", 1);
        int level61 = NeedDemotion.LEVEL_6;
        Log.e("JACK", level61 + "");
    }

    public void test(int i, int b) {
        new person();
        new person(1);
    }


    private void AfterThrowingTest() {
        try {
            View v = null;
            v.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void click4(View view) {
        try {
            AfterThrowingTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Catch("click")
    public void click5(View view) {
        testCatch();
    }

    private void testCatch() {
        View v = null;
        v.setVisibility(View.VISIBLE);
    }
}