package com.java.example;

import java.lang.reflect.Method;

/**
 * @author:無忌
 * @date:2020/9/8
 * @description:
 */
class ClassLoaderDemo {
    public static void main(String[] args) {
        ClassLoaderDemo demo = new ClassLoaderDemo();

        //demo.printClassLoader();

        demo.testClassIdentity();
    }

    private void printClassLoader() {
        ClassLoader classLoader = ClassLoaderDemo.class.getClassLoader();
        while (classLoader != null) {
            System.out.println(classLoader.toString());
            classLoader = classLoader.getParent();
        }
    }

    private void testClassIdentity() {
        String classDataRootPath = "/Users/dhw/workspace/codes/AndroidDemo/app/build/intermediates/javac/debug/classes";
        FileSystemClassLoader fsc1 = new FileSystemClassLoader(true, classDataRootPath);
        FileSystemClassLoader fsc2 = new FileSystemClassLoader(true, classDataRootPath);
        String className = "com.java.example.Sample";
        try {
            Class class1 = fsc1.loadClass(className);
            Object object1 = class1.newInstance();
            Class class2 = fsc2.loadClass(className);
            Object object2 = class2.newInstance();
            Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class);
            setSampleMethod.invoke(object1, object2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
