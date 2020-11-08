package com.algorithm.example.string;

/**
 * @author:無忌
 * @date:2020/10/20
 * @description:5.替换空格
 */
class P51_ReplaceSpaces {
    public static String replaceSpace(StringBuffer str) {
        char[] ch = str.toString().toCharArray();
        int spaceNum = 0;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == ' ') {
                spaceNum++;
            }
        }
        char[] ch1 = new char[spaceNum * 2 + str.length()];
        int len = spaceNum * 2 + str.length() - 1;
        for (int i = ch.length - 1; i >= 0; i--) {
            if (ch[i] != ' ') {
                ch1[len--] = ch[i];
            } else {
                ch1[len--] = '0';
                ch1[len--] = '2';
                ch1[len--] = '%';
            }
        }
        return String.valueOf(ch1);
    }

    public static void main(String[] args) {
        String data = "We are happy.";
        System.out.println(data);
        String newData = replaceSpace(new StringBuffer(data));
        System.out.println(newData);
    }
}
