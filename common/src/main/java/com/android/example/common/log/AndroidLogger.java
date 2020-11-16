package com.android.example.common.log;

import android.util.Log;

/**
 * @author:無忌
 * @date:2020/11/4
 * @description:
 */
class AndroidLogger implements ILogger {
    private static final AndroidLogger instance = new AndroidLogger();

    static AndroidLogger getInstance() {
        return instance;
    }

    private AndroidLogger() {
    }

    @Override
    public void d(String tag, String message) {
        Log.d(tag, message);
    }

    @Override
    public void i(String tag, String message) {
        Log.i(tag, message);
    }

    @Override
    public void e(String tag, String message) {
        Log.e(tag, message);
    }

    @Override
    public void e(String tag, String message, Throwable throwable) {
        Log.e(tag, message, throwable);
    }
}
