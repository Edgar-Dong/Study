package com.android.example.thirdlib.hilt;

import com.android.example.common.log.Logger;

import javax.inject.Inject;

/**
 * @author:無忌
 * @date:2020/12/7
 * @description:
 */
class EletricEngine implements Engine {
    @Inject
    public EletricEngine(){

    }

    @Override
    public void start() {
        Logger.get().d(EletricEngine.class.getSimpleName(),"Electric engine start");
    }

    @Override
    public void shutdown() {
        Logger.get().d(EletricEngine.class.getSimpleName(),"Electric engine shutdown");
    }
}
