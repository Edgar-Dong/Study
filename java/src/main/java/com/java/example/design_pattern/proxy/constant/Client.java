package com.java.example.design_pattern.proxy.constant;

/**
 * @author:無忌
 * @date:2021/1/17
 * @description:
 */
class Client {
    public static void main(String[] args) {
        AbstractObject abstractObject = new ProxyObject();
        abstractObject.operation();
    }
}
