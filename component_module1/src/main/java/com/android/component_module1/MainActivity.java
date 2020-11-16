package com.android.component_module1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.example.common.log.Logger;


@Route(path = "/module1/main")
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "module1:MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m1_activity_main);
        String comFromMsg = "Module1:从模块Module1自身启动";
        if (getIntent() != null && getIntent().getStringExtra("comFromMsg") != null) {
            comFromMsg = getIntent().getStringExtra("comFromMsg");
        }
        Logger.get().d(TAG, "onCreate:" + comFromMsg);
        TextView tvMessage = findViewById(R.id.tvMessage);
        tvMessage.setText(comFromMsg);
    }

    public void jumpModule2(View view) {
        String comFromMsg = "Module1:从Module1跳转到Module2";
        ARouter.getInstance().build("/module2/main").withString("comFromMsg", comFromMsg).navigation();
    }
}