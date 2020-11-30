package com.android.example.thirdlib.dagger.demo03;

import com.android.example.MainApp;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author:無忌
 * @date:2020/11/27
 * @description:
 */
@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        ActivityModule.class})
public interface AppComponent3 {
    void inject(MainApp app);

    @Component.Builder
    interface Builder {
        AppComponent3 build();
    }
}
