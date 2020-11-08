package com.algorithm.example.tree;

/**
 * @author:無忌
 * @date:2020/10/15
 * @description:
 */
class Test {
    public static void main(String[] args) {
        String str = "0,1,2,3,4,5,6";
        StringBuilder stringBuilder = new StringBuilder(str);
        System.out.println(stringBuilder.indexOf(","));
        String str1 = stringBuilder.substring(0,stringBuilder.indexOf(","));
        System.out.println(str1);

    }
}
