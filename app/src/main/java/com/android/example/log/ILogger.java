package com.android.example.log;

/**
 * @author:無忌
 * @date:2020/8/26
 * @description:
 */
public interface ILogger {
    void d(String tag, String message);

    void i(String tag, String message);

    void e(String tag, String message);

    void e(String tag, String message, Throwable throwable);
}
