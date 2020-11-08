package com.java.example;


import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * @author:無忌
 * @date:2020/9/1
 * @description:
 */
class ReferenceDemo {
    //-Xms10M -Xmx10M -Xmn5M -XX:+PrintGCDetails
    public void soft(){
        //100M的缓存数据
        byte[] cacheData = new byte[2 * 1024 * 1024];
        //将缓存数据用软引用持有
        SoftReference<byte[]> cacheRef = new SoftReference<>(cacheData);
        //将缓存数据的强引用去除
        cacheData = null;
        System.out.println("第一次GC前" + cacheData);
        System.out.println("第一次GC前" + cacheRef.get());
        //进行一次GC后查看对象的回收情况
        System.gc();
        //等待GC
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("第一次GC后" + cacheData);
        System.out.println("第一次GC后" + cacheRef.get());

        SoftReference[] softArr = new SoftReference[5];
        softArr[0] = new SoftReference<byte[]>(new byte[1024*1024*2]);
        softArr[1] = new SoftReference<byte[]>(new byte[1024*1024*2]);
        softArr[2] = new SoftReference<byte[]>(new byte[1024*1024*2]);
        softArr[3] = new SoftReference<byte[]>(new byte[1024*1024*2]);

        System.out.println("分配后" + cacheData);
        System.out.println("分配后" + cacheRef.get());
    }

    public void weak(){
        //100M的缓存数据
        byte[] cacheData = new byte[100 * 1024 * 1024];
        //将缓存数据用软引用持有
        WeakReference<byte[]> cacheRef = new WeakReference<>(cacheData);
        System.out.println("第一次GC前" + cacheData);
        System.out.println("第一次GC前" + cacheRef.get());
        //进行一次GC后查看对象的回收情况
        System.gc();
        //等待GC
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("第一次GC后" + cacheData);
        System.out.println("第一次GC后" + cacheRef.get());

        //将缓存数据的强引用去除
        cacheData = null;
        System.gc();
        //等待GC
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("第二次GC后" + cacheData);
        System.out.println("第二次GC后" + cacheRef.get());
    }

    //-Xms10M -Xmx10M -Xmn5M -XX:+PrintGCDetails
    public void soft2(){
        A a = new A();
        ReferenceQueue<A> rq = new ReferenceQueue<>();
        SoftReference<A> wrA = new SoftReference<>(a,rq);
        System.out.println("初始化， 强引用对象a：" + a + "， 软引用引用的对象a：" + wrA.get());

        a = null;
        System.out.println("解除对象a对应的强引用a：" + a);

        if (wrA.get() == null) {
            System.out.println("a对象进入垃圾回收流程");
        } else {
            System.out.println("a对象未进入垃圾回收流程：" + wrA.get());
        }

        System.out.println("通知虚拟机进行GC回收");
        System.gc();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SoftReference[] softArr = new SoftReference[5];
        softArr[0] = new SoftReference<byte[]>(new byte[1024*1024*2]);
        softArr[1] = new SoftReference<byte[]>(new byte[1024*1024*2]);
        softArr[2] = new SoftReference<byte[]>(new byte[1024*1024*2]);
        softArr[3] = new SoftReference<byte[]>(new byte[1024*1024*2]);

        if (wrA.get() == null) {
            System.out.println("a对象进入垃圾回收流程");
        } else {
            System.out.println("a对象未进入垃圾回收流程：" + wrA.get());
        }

        System.out.println("软引用引用队列中被回收的对象引用：" + rq.poll());
    }

    public void weak2(){
        A a = new A();
        ReferenceQueue<A> rq = new ReferenceQueue<>();
        WeakReference<A> wrA = new WeakReference<>(a,rq);
        System.out.println("初始化， 强引用对象a：" + a + "， 弱引用引用的对象a：" + wrA.get());

        a = null;
        System.out.println("解除对象a对应的强引用a：" + a);

        if (wrA.get() == null) {
            System.out.println("a对象进入垃圾回收流程");
        } else {
            System.out.println("a对象未进入垃圾回收流程：" + wrA.get());
        }

        System.out.println("通知虚拟机进行GC回收");
        System.gc();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (wrA.get() == null) {
            System.out.println("a对象进入垃圾回收流程");
        } else {
            System.out.println("a对象未进入垃圾回收流程：" + wrA.get());
        }

        System.out.println("弱引用引用队列中被回收的对象引用：" + rq.poll());
    }

    static class A {
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("in A finalize");
        }
    }

    public static void main(String[] args) {
        ReferenceDemo referenceDemo = new ReferenceDemo();
        referenceDemo.soft();
        //referenceDemo.weak();

        //referenceDemo.soft2();
        //referenceDemo.weak2();
    }
}
