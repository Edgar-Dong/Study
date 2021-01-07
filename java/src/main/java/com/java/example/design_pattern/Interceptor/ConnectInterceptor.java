package com.java.example.design_pattern.Interceptor;

/**
 * @author:無忌
 * @date:2020/12/23
 * @description:连接拦截器
 */
public final class ConnectInterceptor implements Interceptor {
    @Override
    public void intercept(Chain chain) {
        System.out.println(getClass().getSimpleName() + ":拦截方法执行");
        chain.proceed();
    }
}
