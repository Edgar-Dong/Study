package com.android.example.thirdlib.hilt;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

/**
 * @author:無忌
 * @date:2020/12/7
 * @description:
 */
@Module
@InstallIn(ActivityComponent.class)
abstract class EngineModule {
    @BindGasEngine
    @Binds
    abstract Engine bindGasEngine(GasEngine gasEngine);

    @BindElectricEngine
    @Binds
    abstract Engine bindElectricEngine(EletricEngine eletricEngine);

}
