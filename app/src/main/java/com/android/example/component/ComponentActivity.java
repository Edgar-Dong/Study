package com.android.example.component;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.example.R;

@Route(path = "/app/component")
public class ComponentActivity extends AppCompatActivity {
    private static final String TAG = "ComponentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component);
    }

    public void jumpModule1(View view) {
        String comFromMsg = "App:从主模块跳转到Module1";
        ARouter.getInstance().build("/module1/main").withString("comFromMsg", comFromMsg).navigation();
    }

    public void jumpModule2(View view) {
        String comFromMsg = "App:从主模块跳转到Module2";
        ARouter.getInstance().build("/module2/main").withString("comFromMsg", comFromMsg).navigation();
    }
}