package com.android.component_module2.thirdlib.dagger.mvp.model;

import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;

/**
 * @author:無忌
 * @date:2020/11/30
 * @description:
 */
@Module
public class ThirdModel {
    @Provides
    public ThirdClass providerThirdClass() {
        return new ThirdClass();
    }

    @Provides
    public SharedPreferences providerSharedPreferences(Context context) {
        return context.getSharedPreferences("dagger_mvp_demo", Context.MODE_PRIVATE);
    }
}
