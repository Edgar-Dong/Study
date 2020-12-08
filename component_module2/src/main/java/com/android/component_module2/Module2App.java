package com.android.component_module2;

import android.app.Activity;
import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.android.component_module2.thirdlib.dagger.demo02.DaggerAppComponent2;
import com.android.component_module2.thirdlib.dagger.demo03.DaggerAppComponent3;
import com.android.component_module2.thirdlib.dagger.mvp.component.DaggerMvpAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * @author:無忌
 * @date:2020/11/16
 * @description:
 */
public class Module2App extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);

        DaggerAppComponent2.builder().build().inject(this);
        DaggerAppComponent3.builder().build().inject(this);
        DaggerMvpAppComponent.builder().context(this).build().inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

}
