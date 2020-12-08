package com.android.example;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import dagger.hilt.android.HiltAndroidApp;

/**
 * @author:無忌
 * @date:2020/11/16
 * @description:
 */
@HiltAndroidApp
public class MainApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
