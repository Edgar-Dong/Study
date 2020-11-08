package com.algorithm.example.hash;

import java.util.HashMap;

/**
 * @author:無忌
 * @date:2020/10/29
 * @description:50.第一个只出现一次的字符 题目要求：
 * 字符串中第一个只出现一次的字符。在字符串中找出第一个只出现一次的字符。如输入abaccdeff，则输出b。
 */
class P243_FirstNotRepeatingChar {
    public static char firstUniqChar(String s) {
        HashMap<Character, Boolean> map = new HashMap<>();
        char[] sc = s.toCharArray();
        for (char c : sc) {
            map.put(c, !map.containsKey(c));
        }
        for (char c : sc) {
            if (map.get(c)) {
                return c;
            }
        }
        return ' ';
    }

    public static void main(String[] args) {
        System.out.println(firstUniqChar("abaccdeff"));//b
    }
}
