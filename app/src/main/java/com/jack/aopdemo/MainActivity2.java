package com.jack.aopdemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jack.aspaop.annotation.NeedDemotion;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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
}