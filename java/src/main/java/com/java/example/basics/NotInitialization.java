package com.java.example.basics;

/**
 * @author:無忌
 * @date:2020/11/23
 * @description:
 */
class NotInitialization {
    static class SuperClass {
        static {
            System.out.println("SuperClass init!");
        }

        public static int value = 123;
    }

    static class SubClass extends SuperClass {
        static {
            System.out.println("SubClass init!");
        }
    }

    static class ConstClass {

        static {
            System.out.println("ConstClass init!");
        }

        public static final String HELLOWORLD = "hello world";
    }

    static class Handler {
        public static User user = new User();
        static {
            System.err.println("static code block");
        }
        {
            System.err.println("code block");
        }
        public Handler(){
            System.err.println("Constructor");
        }
        public static void print(){
            System.err.println("static method");
        }
    }

    static class User {
        public User() {
            System.err.println("user initing===>");
        }
    }

    public static void main(String[] args) {
        //不会触发父类的初始化，输出：SuperClass init! 123
        //System.out.println(SubClass.value);

        //通过数组定义来引用类，不会触发此类的初始化
        //SuperClass[] sca = new SuperClass[10];

        //常量池不会触发此类的初始化，输出：hello world
        //System.out.println(ConstClass.HELLOWORLD);

        //调用目标类的静态变量或静态方法时，不会触发该类的代码块或构造方法
        //System.err.println(Handler.user);
        Handler.print();
    }
}
