package com.algorithm.example.hash;

/**
 * @author:無忌
 * @date:2020/10/29
 * @description:50.2.流中第一个出现一次的字符 题目要求：
 * 找出字符流中第一个只出现一次的字符。例如，当从字符流google中只读出前两个字符go时，第一个只出现一次的字符是g；当读完google时，第一个只出现一次的字符是l。
 */
class P247_FirstNotRepeatingCharInStream {
    public static class CharStatistics {
        private int index;
        private int[] occurence;

        public CharStatistics() {
            index = 0;
            occurence = new int[256];
            for (int i = 0; i < 256; i++) {
                occurence[i] = -1;
            }
        }

        public void insert(char c) {
            if (occurence[c] == -1) {
                occurence[c] = index;
            } else if (occurence[c] >= 0) {
                occurence[c] = -2;
            }
            index++;
        }

        public char findFirst() {
            char ch = '?';
            int minIndex = Integer.MAX_VALUE;
            for (int i = 0; i < 256; i++) {
                if (occurence[i] >= 0 && occurence[i] < minIndex) {
                    ch = (char) i;
                    minIndex = occurence[i];
                }
            }
            return ch;
        }
    }

    public static void main(String[] args) {
        String str = "google";
        CharStatistics charStatistics = new CharStatistics();
        for (int i = 0; i < str.length(); i++) {
            charStatistics.insert(str.charAt(i));
            System.out.print(charStatistics.findFirst());
        }
    }
}
