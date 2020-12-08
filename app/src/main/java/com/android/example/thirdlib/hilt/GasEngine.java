package com.android.example.thirdlib.hilt;

import com.android.example.common.log.Logger;

import javax.inject.Inject;

/**
 * @author:無忌
 * @date:2020/12/7
 * @description:
 */
class GasEngine implements Engine {
    @Inject
    public GasEngine(){

    }

    @Override
    public void start() {
        Logger.get().d(GasEngine.class.getSimpleName(), "Gas engine start");
    }

    @Override
    public void shutdown() {
        Logger.get().d(GasEngine.class.getSimpleName(), "Gas engine shutdown");
    }
}
