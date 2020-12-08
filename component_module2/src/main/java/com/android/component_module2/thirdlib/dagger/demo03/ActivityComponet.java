package com.android.component_module2.thirdlib.dagger.demo03;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * @author:無忌
 * @date:2020/11/30
 * @description:
 */
@Subcomponent(modules = {AndroidInjectionModule.class})
public interface ActivityComponet extends AndroidInjector<BaseActivity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseActivity> {
    }
}
