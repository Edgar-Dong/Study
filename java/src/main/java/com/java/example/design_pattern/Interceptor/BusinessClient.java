package com.java.example.design_pattern.Interceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:無忌
 * @date:2020/12/23
 * @description:
 */
class BusinessClient {
    public void getInterceptorChain() {
        List<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(new CacheInterceptor());
        interceptors.add(new ConnectInterceptor());
        Interceptor.Chain chain = new RealInterceptorChain(interceptors, 0, this);
        chain.proceed();
    }

    public void performBusiness() {
        System.out.println("执行业务逻辑");
    }
}
