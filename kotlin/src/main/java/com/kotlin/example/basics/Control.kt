package com.kotlin.example.basics

/**
 * @author:無忌
 * @date:2020/12/5
 * @description:
 */
fun main(args: Array<String>) {
    forStatement()
}

fun forStatement() {
    val items = listOf<String>("apple", "banana", "kiwi")
//    for (item in items) {
//        println(item)
//    }

//    for (index in items.indices){
//        println("item at ${index} is ${items[index]}")
//    }

    loop@ for (i in 1..100) {
        for (j in 1..100) {
            println("i=" + i + ", j=" + j)
            if (i ==5 && j == 5) break@loop
        }
    }
}

fun whenStatement2() {
    val items = setOf("apple", "banana", "kiwi")
    when {
        "orange" in items -> print("juicy")
        "apple" in items -> print("apple is fine too")
    }
}

fun whenStatement(x: Int) {
//    when (x) {
//        1 -> print("x == 1")
//        2 -> print("x == 2")
//        else -> {
//            print("x不是1，也不是2")
//        }
//    }

//    when(x){
//        0,1 -> print("x == 0 or x ==1")
//        else -> print("otherwise")
//    }

    when (x) {
        in 1..10 -> print("x is in the range")
        !in 10..20 -> print("x is outside the range")
        else -> print("none of the above")
    }
}

fun hasPrefix(x: Any) = when (x) {
    is String -> x.startsWith("prefix")
    else -> false
}