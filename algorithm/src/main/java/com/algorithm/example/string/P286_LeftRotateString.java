package com.algorithm.example.string;

/**
 * @author:無忌
 * @date:2020/10/21
 * @description:58.2.左旋转字符串
 */
class P286_LeftRotateString {
    public static String leftRotateString(String str, int n) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = n; i < str.length(); i++) {
            stringBuilder.append(str.charAt(i));
        }
        for (int i = 0; i < n; i++) {
            stringBuilder.append(str.charAt(i));
        }
        return stringBuilder.toString();
    }

    public static String leftRotateString2(String str, int n) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = n; i < n + str.length(); i++) {
            stringBuilder.append(str.charAt(i % str.length()));
        }
        return stringBuilder.toString();
    }

    public static String leftRotateString3(String str, int n) {
        String res = "";
        for (int i = n; i < n + str.length(); i++) {
            res += str.charAt(i % str.length());
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "abcdefg";
        System.out.println(leftRotateString(s, 2));
        System.out.println(leftRotateString2(s, 2));
        System.out.println(leftRotateString3(s, 2));
    }
}
