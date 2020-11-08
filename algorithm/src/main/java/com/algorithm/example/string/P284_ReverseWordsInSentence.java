package com.algorithm.example.string;

/**
 * @author:無忌
 * @date:2020/10/20
 * @description:58.翻转单词顺序
 */
class P284_ReverseWordsInSentence {
    public static String reverse(String str) {
        str = str.trim();
        int j = str.length() - 1, i = j;
        StringBuffer stringBuffer = new StringBuffer();
        while (i >= 0) {
            while (i >= 0 && str.charAt(i) != ' ') {
                i--;
            }
            stringBuffer.append(str.substring(i + 1, j + 1) + " ");
            while (i >= 0 && str.charAt(i) == ' ') {
                i--;
            }
            j = i;
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        String string = "I am a student.";
        System.out.println("orign:" + string);
        System.out.println("reverse:" + reverse(string));
    }
}
