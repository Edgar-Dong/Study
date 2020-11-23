package com.java.example.basics;

/**
 * @author:無忌
 * @date:2020/11/23
 * @description:继承类初始化顺序
 * @1(父类静态变量)->@2(父类静态代码块)->@3(子类静态变量)->@4(子类静态代码块)->
 * @5(父类变量)->@6(父类代码块)->@7(父类构造函数)->@8(子类变量)->@9(子类代码块)->@10(子类构造函数)
 */
class Parent {

    //@1
    public static String p_StaticField = "父类--静态变量";
    //@5
    public String p_Field = "父类--变量";
    protected int i = 9;
    protected int j = 0;

    //@2
    static {
        System.out.println(p_StaticField);
        System.out.println("父类--静态初始化块");
    }

    //@6
    {
        System.out.println(p_Field);
        System.out.println("父类--初始化块");
    }

    //@7
    public Parent() {
        System.out.println("父类--构造器");
        System.out.println("i=" + i + ", j=" + j);
        j = 20;
    }

    static class SubClass extends Parent {
        //@3
        public static String s_StaticField = "子类--静态变量";
        //@8
        public String s_Field = "子类--变量";

        //@4
        static {
            System.out.println(s_StaticField);
            System.out.println("子类--静态初始化块");
        }

        //@9
        {
            System.out.println(s_Field);
            System.out.println("子类--初始化块");
        }

        //@10
        public SubClass() {
            System.out.println("子类--构造器");
            System.out.println("i=" + i + ",j=" + j);
        }

        public static void main(String[] args) {
            System.out.println("子类main方法");
            new SubClass();
        }
    }
}
