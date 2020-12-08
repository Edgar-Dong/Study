package com.android.component_module2.thirdlib.dagger.demo03;


import com.android.component_module2.Module2App;

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
    void inject(Module2App app);

    @Component.Builder
    interface Builder {
        AppComponent3 build();
    }
}
