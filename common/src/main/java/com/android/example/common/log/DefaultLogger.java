package com.android.example.common.log;

import android.util.Log;

/**
 * @author:無忌
 * @date:2020/8/26
 * @description:
 */
class DefaultLogger implements ILogger {
    private static final DefaultLogger instance = new DefaultLogger();
    private static final String TAG = "DefaultLogger";
    private static final int LOG_LEVEL = Log.ERROR;

    private DefaultLogger() {
    }

    static ILogger getInstance() {
        return instance;
    }

    @Override
    public void d(String tag, String message) {
        if (LOG_LEVEL >= Log.DEBUG) {
            Log.d(TAG, "[" + tag + "]=>>" + message);
        }
    }

    @Override
    public void i(String tag, String message) {
        if (LOG_LEVEL >= Log.INFO) {
            Log.i(TAG, "[" + tag + "]=>>" + message);
        }
    }

    @Override
    public void e(String tag, String message) {
        if (LOG_LEVEL >= Log.ERROR) {
            Log.e(TAG, "[" + tag + "]=>>" + message);
        }
    }

    @Override
    public void e(String tag, String message, Throwable throwable) {
        if (LOG_LEVEL >= Log.ERROR) {
            Log.e(TAG, "[" + tag + "]=>>" + message, throwable);
        }
    }
}
