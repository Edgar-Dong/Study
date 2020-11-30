package com.android.example.thirdlib.dagger;

import dagger.Module;
import dagger.Provides;

/**
 * @author:無忌
 * @date:2020/11/27
 * @description:
 */
@Module
public class ClazzEModule {
    ClazzE clazzE;

    @Provides
    public ClazzE providerClazzE() {
        return new ClazzE();
    }
}
