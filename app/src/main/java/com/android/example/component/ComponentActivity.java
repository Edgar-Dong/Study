package com.android.example.component;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.example.BuildConfig;
import com.android.example.R;

@Route(path = "/app/component")
public class ComponentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component);

        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(getApplication());
    }

    public void jumpModule1(View view) {
        String comFromMsg = "我从app模块来";
        ARouter.getInstance().build("/module1/main").withString("comfrom", comFromMsg).navigation();
    }

    public void jumpModule2(View view) {
        String comFromMsg = "我从app模块来";
        ARouter.getInstance().build("/module2/main").withString("comfrom", comFromMsg).navigation();
    }
}