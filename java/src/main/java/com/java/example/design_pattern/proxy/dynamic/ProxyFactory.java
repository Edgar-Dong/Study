package com.java.example.design_pattern.proxy.dynamic;

import java.lang.reflect.Proxy;

/**
 * @author:無忌
 * @date:2021/1/17
 * @description:
 */
class ProxyFactory {
    private ITarget target;

    public ProxyFactory(ITarget target) {
        this.target = target;
    }

    public Object getInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new MyInvocationHandler(target));
    }
}
