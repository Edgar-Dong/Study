package com.kotlin.example.basics

/**
 * @author:無忌
 * @date:2020/12/6
 * @description:
 */
interface MyInterface {
    var name: String
    fun bar()
    fun foo() {
        println("foo")
    }
}

class Child : MyInterface {
    override var name: String = "runoob"

    override fun bar() {
        println("bar")
    }

}

interface AA {
    fun foo() {
        println("AA")
    }

    fun bar()
}

interface BB {
    fun foo() {
        println("BB")
    }

    fun bar() {
        println("bar")
    }

}

class CC : AA {
    override fun bar() {
        println("bar")
    }
}

class DD : AA, BB {
    override fun foo() {
        super<AA>.foo()
        super<BB>.foo()
    }

    override fun bar() {
        super<BB>.bar()
    }
}

fun main(args: Array<String>) {
//    val c = Child()
//    c.foo()
//    c.bar()
//    println(c.name)

    val d = DD()
    d.foo()
    d.bar()
}