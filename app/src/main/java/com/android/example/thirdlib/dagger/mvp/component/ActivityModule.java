package com.android.example.thirdlib.dagger.mvp.component;

import com.android.example.thirdlib.dagger.mvp.DaggerMvpActivity;
import com.android.example.thirdlib.dagger.mvp.model.ThirdModel;

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
