package com.android.example.memoryleak;

import android.content.Context;

/**
 * @author:無忌
 * @date:2020/9/9
 * @description:
 */
class MySingleton {
    private Context context;

    private MySingleton(Context context) {
        this.context = context;
    }

    private static volatile MySingleton mInstance;

    public static MySingleton getInstance(Context context) {
        if (mInstance == null) {
            synchronized (MySingleton.class) {
                if (mInstance == null) {
                    mInstance = new MySingleton(context);
                }
            }
        }
        return mInstance;
    }

    public String getName(){
        return "MySingleton";
    }
}
