package com.android.example.thirdlib.dagger.demo02;

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
        Dagger2ActivityModule.class})
public interface AppComponent2 {
    void inject(MainApp app);

    @Component.Builder
    interface Builder {
        AppComponent2 build();
    }
}
