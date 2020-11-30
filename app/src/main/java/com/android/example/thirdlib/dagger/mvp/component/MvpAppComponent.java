package com.android.example.thirdlib.dagger.mvp.component;

import android.content.Context;

import com.android.example.MainApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author:無忌
 * @date:2020/11/30
 * @description:
 */
@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        ActivityModule.class
})
public interface MvpAppComponent {
    void inject(MainApp app);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder context(Context context);
        MvpAppComponent build();
    }
}
