package com.android.component_module2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.example.common.log.Logger;

@Route(path = "/module2/main")
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "module2:MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m2_activity_main);
        String comFromMsg = "Module2:从模块Module2自身启动";
        if (getIntent() != null && getIntent().getStringExtra("comFromMsg") != null) {
            comFromMsg = getIntent().getStringExtra("comFromMsg");
        }
        Logger.get().d(TAG, "onCreate:" + comFromMsg);
        TextView tvMessage = findViewById(R.id.tvMessage);
        tvMessage.setText(comFromMsg);
    }
}