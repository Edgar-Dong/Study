package com.kotlin.example.basics

/**
 * @author:無忌
 * @date:2020/12/6
 * @description:
 */
fun MutableList<Int>.swap(index1: Int, index2: Int) {
    var temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}

open class CCC

class DDD : CCC()

fun CCC.foo() = "C"

fun DDD.foo() = "D"

fun printFoo(c: CCC) {
    println(c.foo())
}

class MyClass {
    fun foo() {
        print("成员函数")
    }
}

fun MyClass.foo() {
    print("扩展函数")
}


fun main(args: Array<String>) {
//    val l = mutableListOf<Int>(1, 2, 3)
//    l.swap(0, 2)
//
//    println(l.toString())

//    printFoo(DDD())

    var myClass = MyClass()
    myClass.foo()
}