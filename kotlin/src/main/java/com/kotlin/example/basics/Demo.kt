package com.kotlin.example.basics

/**
 * @author:無忌
 * @date:2020/12/5
 * @description:
 */
open class Base {
    open fun f() {}
}

abstract class Derived : Base() {
    override abstract fun f()
}

/**
 * 嵌套类
 */
class Outer {
    private val bar: Int = 1

    class Nested {
        fun foo() = 2
    }
}

fun testOuter() {
    val demo = Outer.Nested().foo()
    println(demo)
}

class Outer2 {
    private val bar: Int = 1
    var v = "成员属性"

    /**
     * 嵌套内部类
     */
    inner class Inner {
        fun foo() = bar//访问外部类成员
        fun innerTest() {
            var o = this@Outer2//获取外部类的成员变量
            println("内部类可以引用外部类的成员，例如：" + o.v)
        }
    }
}

fun testOuter2() {
    val demo = Outer2().Inner().foo()
    println(demo)
    val demo2 = Outer2().Inner().innerTest()
    println(demo2)
}

class Test {
    var v = "成员属性"
    fun setInterFace(test: TestInterface) {
        test.test()
    }
}

interface TestInterface {
    fun test()
}

fun testAnonymousInnerClass() {
    var test = Test()
    test.setInterFace(object : TestInterface {
        override fun test() {
            //TODO("Not yet implemented")
            println("对象表达式创建匿名内部类的实例")
        }

    })
}

fun main(args: Array<String>) {
    testAnonymousInnerClass()
}