package com.android.component_module1;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author:無忌
 * @date:2020/11/16
 * @description:
 */
public class Module1App extends Application {
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
