package com.java.example.design_pattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author:無忌
 * @date:2021/1/17
 * @description:
 */
public class MyInvocationHandler implements InvocationHandler {
    private ITarget target;

    public MyInvocationHandler(ITarget target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理对象:before do something");
        Object result = method.invoke(target, args);
        System.out.println("代理对象:after do something");
        return result;
    }
}
