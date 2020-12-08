package com.android.component_module2.thirdlib.dagger.demo01;

import com.android.component_module2.thirdlib.dagger.ClazzEModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author:無忌
 * @date:2020/11/27
 * @description:
 */
@Singleton
@Component(modules = {
        ClazzEModule.class
})
public interface AppComponent {
    void inject(DaggerActivity activity);

    @Component.Builder
    interface Builder {
        AppComponent build();
    }
}
