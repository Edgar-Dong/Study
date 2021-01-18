package com.java.example.design_pattern.proxy.dynamic;

/**
 * @author:無忌
 * @date:2021/1/17
 * @description:
 */
class Client {
    public static void main(String[] args) {
        ITarget target = new Target();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        ITarget proxy = (ITarget) proxyFactory.getInstance();
        proxy.doSomething();
    }
}
