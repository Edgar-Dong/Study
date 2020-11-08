package com.algorithm.example.string;

/**
 * @author:無忌
 * @date:2020/10/26
 * @description:
 */
class P127_NumberStrings {
    public static boolean isNumeric(String s) {
        char[] str = s.toCharArray();
        if (str == null || str.length == 0) {
            return false;
        }
        //标记是否遇到数位、小数点、'e'或'E'
        boolean numFlag = false, dotFlag = false, eFlag = false;
        for (int i = 0; i < str.length; i++) {
            if (str[i] >= '0' && str[i] <= '9') {
                // 判定为数字
                numFlag = true;
            } else if (str[i] == '.' && !dotFlag && !eFlag) {
                // 判定为.  需要没出现过.并且没出现过e
                dotFlag = true;
            } else if ((str[i] == 'e' || str[i] == 'E') && !eFlag && numFlag) {
                // 判定为e 需要没出现过e，并且出现过数字了
                eFlag = true;
                numFlag = false;//重置isNum，因为‘e’或'E'之后也必须接上整数，防止出现 123e或者123e+的非法情况
            } else if ((str[i] == '+' || str[i] == '-') && (i == 0 || str[i - 1] == 'e' || str[i - 1] == 'E')) {

            } else {
                return false;
            }
        }
        return numFlag;
    }

    public static void main(String[] args) {
        System.out.println(isNumeric("+100"));//true
        System.out.println(isNumeric("5e2")); //true
        System.out.println(isNumeric("-123"));//true
        System.out.println(isNumeric("3.1416"));//true
        System.out.println(isNumeric("-1E-16"));//true
        System.out.println(isNumeric(".6"));//true
        System.out.println(isNumeric("6."));//true
        System.out.println(isNumeric("12e"));//false
        System.out.println(isNumeric("1a3.14"));//false
        System.out.println(isNumeric("1.2.3"));//false
        System.out.println(isNumeric("+-5"));//false
        System.out.println(isNumeric("12e+5.4"));//false
    }
}
