package com.android.component_module2.thirdlib.dagger.demo02;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

/**
 * @author:無忌
 * @date:2020/11/27
 * @description:
 */
@Module(subcomponents = Dagger2ActivitySubComponent.class)
public abstract class Dagger2ActivityModule {
    @Binds
    @IntoMap
    @ClassKey(Dagger2Activity.class)
    abstract AndroidInjector.Factory<?> bindDagger2ActivityInjectorFactory(Dagger2ActivitySubComponent.Builder builder);
}
