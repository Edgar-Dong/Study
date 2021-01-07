package com.java.example.design_pattern.Interceptor;

import java.util.List;

/**
 * @author:無忌
 * @date:2020/12/23
 * @description:调度器
 */
public final class RealInterceptorChain implements Interceptor.Chain {
    private final List<Interceptor> interceptors;
    private final int index;
    private final BusinessClient client;

    public RealInterceptorChain(List<Interceptor> interceptors, int index, BusinessClient client) {
        this.interceptors = interceptors;
        this.index = index;
        this.client = client;
    }

    @Override
    public void proceed() {
        if (index == interceptors.size()) {
            client.performBusiness();
        } else {
            Interceptor.Chain chain = new RealInterceptorChain(interceptors, index + 1, client);
            Interceptor interceptor = interceptors.get(index);
            interceptor.intercept(chain);
        }
    }
}
