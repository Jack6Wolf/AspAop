package com.jack.aopdemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jack.aspaop.annotation.NeedDemotion;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        boolean test = test();
        Log.e("JACK", test + "");
    }

    @SuppressLint("SupportAnnotationUsage")
    @NeedDemotion(NeedDemotion.LEVEL_6)
    private boolean test() {
        boolean is = true;
        Toast.makeText(this, "点击了我", Toast.LENGTH_SHORT).show();
        is = false;
        return is;
    }

    public void click1(View view) {
        test1();
    }

    @SuppressLint("SupportAnnotationUsage")
    @NeedDemotion(NeedDemotion.LEVEL_6)
    private void test1() {
        Toast.makeText(this, "点击了我", Toast.LENGTH_SHORT).show();
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
}