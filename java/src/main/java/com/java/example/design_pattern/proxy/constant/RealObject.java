package com.java.example.design_pattern.proxy.constant;

/**
 * @author:無忌
 * @date:2021/1/17
 * @description:
 */
public class RealObject extends AbstractObject {
    @Override
    public void operation() {
        System.out.println("do something");
    }
}
