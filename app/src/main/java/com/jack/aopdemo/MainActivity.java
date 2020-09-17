package com.jack.aopdemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jack.aspaop.annotation.DurationLog;
import com.jack.aspaop.annotation.NeedDemotion;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SupportAnnotationUsage")
    @NeedDemotion(NeedDemotion.LEVEL_6)
    private static boolean test(int i, boolean flag) {
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
}