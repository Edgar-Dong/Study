package com.algorithm.example.string;

/**
 * @author:無忌
 * @date:2020/10/21
 * @description:67.把字符串转换成整数
 */
class P318_StringToInt {
    public static int strToInt(String str) {
        char ch[] = str.trim().toCharArray();
        if (ch.length == 0) {
            return 0;
        }
        int res = 0, bndry = Integer.MAX_VALUE / 10;
        int i = 1, sign = 1;
        if (ch[0] == '-') {
            sign = -1;
        } else if (ch[0] != '+') {
            i = 0;
        }
        for (int j = i; j < ch.length; j++) {
            if (ch[j] < '0' || ch[j] > '9') {
                break;
            }
            if (res > bndry || res == bndry && ch[i] > '7') {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + (ch[j] - '0');
        }
        return sign * res;
    }

    public static void main(String[] args) {
        String s = " 86 hi 8";
        System.out.println(strToInt(s));
    }
}
