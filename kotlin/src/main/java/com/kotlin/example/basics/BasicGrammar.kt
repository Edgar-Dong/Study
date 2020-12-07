package com.kotlin.example

fun main(args: Array<String>) {
//    println("Hello World")

//    vars(1, 2, 3, 4, 5)

//    val sumLambda: (Int, Int) -> Int = { x, y -> x + y }
//    print(sumLambda(1, 2))

//    val a: Int = 1
//    val b = 2
//    val c: Int
//    c = 3
//    print("a=" + a + ", b=" + b + ", c=" + c)

//    var x = 5
//    x += 1
//    print("x=" + x)

//    var a = 1
//    val s1 = "a is $a"
//    println(s1)//a is 1
//
//    a = 2
//    val s2 = "${s1.replace("is", "was")}, but now is $a"//a was 1,but now is 2
//    println(s2)

//    var age: String? = "23"
//    var age1 = age!!.toInt()//抛出空指针异常
//    println(age1)
//    var age2 = age?.toInt()//不处理返回null
//    println(age2)
//    var age3 = age?.toInt() ?: -1
//    println(age3)

    range()
}

fun add(a: Int, b: Int): Int {
    return a + b
}

fun sum(a: Int, b: Int) = a + b

fun printSum(a: Int, b: Int) {
    print(a + b)
}

fun vars(vararg v: Int) {
    for (param in v) {
        print(param)
    }
}

fun range() {
    print("循环输出:")
    for (i in 1..4) {
        print(i)//1234
    }
    println("\n--------------")
    print("设置步长:")
    for (i in 1..4 step 2) {
        print(i)//13
    }
    println("\n--------------")
    print("使用downTo:")
    for (i in 4 downTo 1 step 2) {
        print(i)//42
    }
    println("\n--------------")
    print("使用until:")
    for (i in 1 until 4) {
        print(i)
    }
}