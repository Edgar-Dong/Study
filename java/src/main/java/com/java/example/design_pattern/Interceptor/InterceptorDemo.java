package com.java.example.design_pattern.Interceptor;

/**
 * @author:無忌
 * @date:2020/12/23
 * @description:
 */
class InterceptorDemo {
    public static void main(String[] args) {
        BusinessClient client = new BusinessClient();
        client.getInterceptorChain();
    }
}
