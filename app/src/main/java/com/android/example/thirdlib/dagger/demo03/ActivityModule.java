package com.android.example.thirdlib.dagger.demo03;

import com.android.example.thirdlib.dagger.ClazzEModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author:無忌
 * @date:2020/11/30
 * @description:
 */
@Module(subcomponents = {ActivityComponet.class})
public abstract class ActivityModule {
    @ContributesAndroidInjector(modules = {ClazzEModule.class})
    abstract Dagger3Activity Dagger3ActivityInjector();
}
