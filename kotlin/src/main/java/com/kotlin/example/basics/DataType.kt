package com.kotlin.example.basics

/**
 * @author:無忌
 * @date:2020/12/5
 * @description:
 */
fun main(array: Array<String>) {
    string()
}

fun string(){
    val text = """
        |多行字符串
        |菜鸟教程
        |Runoob
    """.trimMargin()
    println(text)

    val price = """
        ${'$'}9.99
    """
    println(price)
}

fun array() {
    val a = arrayOf(1, 2, 3)
    val b = Array(3, { i -> (i * 2) })

    println(a[0])
    println(b[0])

    val x: IntArray = intArrayOf(1, 2, 3)
    x[0] = x[1] + x[2]
    println(x[0])
}

/**
 * 操作符
 */
fun bitwiseOperators() {
    val a: Int = 0b10_01_01
    val b: Int = 0b10_10_10

    println("a=" + a.toString(2) + ", b=" + b.toString(2))

    println("b.shl(1) : " + b.shl(1).toString(2))//0b10_10_10_0
    //ob10_10_1,ob10_10_1
    println("b.shr(1) : " + b.shr(1).toString(2) + ", b.ushr(1) : " + b.ushr(1).toString(2))
    //与
    println("a.and(b) : " + a.and(b).toString(2))//0b10_00_00
    //或
    println("a.or(b) : " + a.or(b).toString(2))//0b10_11_11
    //异或
    println("a.xor(b) : " + a.xor(b).toString(2))//0b00_11_11
    //反向(按位非) 公式：~(A) = -(A+1) 结果：-101011
    println("b.inv() : " + b.inv().toString(2))
}

fun conversion() {
    val b: Byte = 1
    val i: Int = b.toInt()
    println("b:" + b + ",i:" + i)
}

/**
 * 字面常量
 */
fun constant() {
    val oneMillion = 1_000_000
    println(oneMillion)
}

/**
 * 类型比较
 */
fun compare() {
    val a: Int = 1000;
    println(a === a)

    val boxA: Int? = a
    val anotherBoxA: Int? = a

    println(boxA === anotherBoxA)
    println(boxA == anotherBoxA)
}