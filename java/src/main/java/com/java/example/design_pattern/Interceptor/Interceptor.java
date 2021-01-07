package com.java.example.design_pattern.Interceptor;

/**
 * @author:無忌
 * @date:2020/12/23
 * @description:拦截器接口
 */
public interface Interceptor {

    interface Chain {
        void proceed();
    }

    void intercept(Chain chain);
}
