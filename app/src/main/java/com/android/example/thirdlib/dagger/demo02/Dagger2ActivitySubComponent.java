package com.android.example.thirdlib.dagger.demo02;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * @author:無忌
 * @date:2020/11/27
 * @description:
 */
@Subcomponent(modules = {
        AndroidInjectionModule.class
})
public interface Dagger2ActivitySubComponent extends AndroidInjector<Dagger2Activity> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<Dagger2Activity> {

    }
}
