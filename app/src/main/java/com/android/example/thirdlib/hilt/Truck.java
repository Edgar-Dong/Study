package com.android.example.thirdlib.hilt;

import com.android.example.common.log.Logger;

import javax.inject.Inject;

/**
 * @author:無忌
 * @date:2020/12/7
 * @description:
 */
class Truck {

    Driver driver;

    @BindGasEngine
    @Inject
    Engine gasEngine;

    @BindElectricEngine
    @Inject
    Engine electricEngine;

    @Inject
    public Truck(Driver driver){
        this.driver = driver;
    }

    public void deliver() {
        gasEngine.start();
        electricEngine.start();
        Logger.get().d(Truck.class.getSimpleName(), "Truck is delivering cargo. Driven by " + driver);
        gasEngine.shutdown();
        electricEngine.shutdown();
    }
}
