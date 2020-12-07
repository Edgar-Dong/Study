package com.kotlin.example.basics

/**
 * @author:無忌
 * @date:2020/12/5
 * @description:
 */
class Person constructor(name: String) {
    init {
        println("name is ${name}")
    }

    var name: String = "wuji"
        get() = field
        set(value) {
            field = value
        }

    var age: Int = 30
        get() = field
        set(value) {
            field = value
        }

    var addr: String = "BeiJing"
        get() = field
        set(value) {
            field = value
        }
}

fun main(args: Array<String>) {
    var person: Person = Person("wuji.dhw")
    person.name = "donghongwei"
    println("name:${person.name}")
    person.age = 30
    println("age:${person.age}")
    person.addr = "yidigang"
    println("addr:${person.addr}")
}