package com.jack.aopdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.jack.aspaop.annotation.Delay;
import com.jack.aspaop.annotation.SingleClick;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViewById(R.id.but0).setOnClickListener(this::click);
        findViewById(R.id.but1).setOnClickListener(this::click);
        findViewById(R.id.but2).setOnClickListener(new View.OnClickListener() {
            @SingleClick(value = 1000, ids = {R.id.but2})
            @Override
            public void onClick(View v) {
                Log.e("JACK", "没使用防抖");
            }
        });
        findViewById(R.id.but3).setOnClickListener(this);

        int i = delayTask();
        Log.e("JACK", "到了！" + i);
    }

    @Delay(delay = 5000)
    private int delayTask() {
        Log.e("JACK", "延迟了！");
        return 1;
    }

    @SingleClick(value = 1000, ids = {R.id.but1})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.but1:
                Log.e("JACK", "使用防抖");
                break;
            case R.id.but2:
                Log.e("JACK", "没使用防抖");
                break;
            case R.id.but0:
                Log.e("JACK", "使用防抖");
                break;
            default:
                break;
        }
    }


    @SingleClick(value = 1000, ids = {R.id.but1, R.id.but3})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but1:
                Log.e("JACK", "使用防抖");
                break;
            case R.id.but2:
                Log.e("JACK", "没使用防抖");
                break;
            case R.id.but3:
                Log.e("JACK", "使用防抖");
                break;
            default:
                break;
        }
    }
}