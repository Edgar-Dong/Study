package com.java.example.design_pattern.proxy.constant;

/**
 * @author:無忌
 * @date:2021/1/17
 * @description:
 */
public class ProxyObject extends AbstractObject {
    private RealObject realObject = new RealObject();
    @Override
    public void operation() {
        System.out.println("before do something");
        realObject.operation();
        System.out.println("after do something");
    }
}
