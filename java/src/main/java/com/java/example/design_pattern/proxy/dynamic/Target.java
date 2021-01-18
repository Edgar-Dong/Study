package com.java.example.design_pattern.proxy.dynamic;

/**
 * @author:無忌
 * @date:2021/1/17
 * @description:
 */
public class Target implements ITarget {
    @Override
    public void doSomething() {
        System.out.println("目标对象:do something");
    }
}
