package com.kotlin.example.basics

/**
 * @author:無忌
 * @date:2020/12/6
 * @description:
 */
open class Graph(name: String) {
    constructor(name: String, area: Int) : this(name) {
        println("-------基类次级构造函数---------")
    }

    open fun paint() {
        println("开始画图形")
    }
}

class Circle : Graph {
    constructor(name: String, area: Int, radius: Int) : super(name, area) {
        println("-------继承类次级构造函数---------")
        println("名称:${name}")
        println("面积:${area}")
        println("半径:${radius}")
    }

    override fun paint() {
        println("开始画圆")
    }
}

open class A {
    open fun f() {
        println("A")
    }

    fun a() {
        println("a")
    }
}

interface B {
    fun f() {
        println("B")
    }

    fun b() {
        println("b")
    }
}

class C() : A(), B {
    override fun f() {
        //TODO("Not yet implemented")
        super<A>.f()
        super<B>.f()
    }
}

fun main(args: Array<String>) {
//    var circle = Circle("圆", 100, 10)
//    circle.paint()

    val c = C()
    c.f()
}