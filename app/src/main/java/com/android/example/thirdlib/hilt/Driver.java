package com.android.example.thirdlib.hilt;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

/**
 * @author:無忌
 * @date:2020/12/7
 * @description:
 */
class Driver {
    private String name = "default";

//    @Inject
//    public Driver(){
//        name = "老李";
//    }

//    @Inject
//    public Driver(@ApplicationContext Context context){
//        name = context.getPackageName();
//    }

//    @Inject
//    public Driver(Application application){
//        name = "App:" + application.getPackageName();
//    }

    @Inject
    public Driver(Activity activity){
        name = "Activity:" + activity.getPackageName();
    }

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                '}';
    }
}
