package com.android.component_module2.thirdlib.dagger.mvp.component;

import com.android.component_module2.thirdlib.dagger.mvp.DaggerMvpActivity;
import com.android.component_module2.thirdlib.dagger.mvp.model.ThirdModel;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author:無忌
 * @date:2020/11/30
 * @description:
 */
@Module(subcomponents = {ActivityComponet.class})
public abstract class ActivityModule {
    @ContributesAndroidInjector(modules = {ThirdModel.class})
    abstract DaggerMvpActivity daggerMvpActivityInjector();
}
