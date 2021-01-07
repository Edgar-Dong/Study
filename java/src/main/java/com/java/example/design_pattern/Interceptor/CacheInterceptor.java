package com.java.example.design_pattern.Interceptor;

/**
 * @author:無忌
 * @date:2020/12/23
 * @description:缓存拦截器
 */
public final class CacheInterceptor implements Interceptor {

    @Override
    public void intercept(Chain chain) {
        System.out.println(getClass().getSimpleName()+":拦截方法执行");
        chain.proceed();
    }
}
