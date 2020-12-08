package com.android.component_module2.thirdlib.dagger.mvp.component;

import android.content.Context;

import com.android.component_module2.Module2App;

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
    void inject(Module2App app);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder context(Context context);
        MvpAppComponent build();
    }
}
