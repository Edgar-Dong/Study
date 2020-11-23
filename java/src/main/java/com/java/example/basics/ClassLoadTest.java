package com.java.example.basics;

/**
 * @author:無忌
 * @date:2020/11/23
 * @description:类初始化顺序
 * @1(静态变量)->@2(静态代码块)->@3(变量)->@4(代码块)->@5(构造函数)
 */
class ClassLoadTest {
    //@1
    private static User user = new User();

    //@2
    static {
        System.out.println("static code block");
    }

    //@3
    {
        System.out.println("code block");
    }

    //@4
    private Student student = new Student();

    //@5
    public ClassLoadTest() {
        System.out.println("Constructor");
    }

    public static void main(String[] args) {
        System.out.println("main=====>>");
        new ClassLoadTest();
    }

    static class Student {
        public Student() {
            System.out.println("student initing===>>");
        }
    }

    static class User {
        public User() {
            System.out.println("user initing===>>");
        }
    }
}
