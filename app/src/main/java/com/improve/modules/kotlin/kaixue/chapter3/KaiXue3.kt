package com.improve.modules.kotlin.kaixue.chapter3

/**
 * Title: KaiXue3
 * <p>
 * Description:
 * </p>
 * @author Changbao
 * @date 2019/8/26  16:36
 */
class KaiXue3 constructor(var name: String) {//「主构造器 primary constructor」

    constructor(name: String, age: Int) : this(name) {

    }

    constructor(name: String, age: Int, isFemale: Boolean) : this(name, age) {

    }

    fun go() {
        println("simple + $name")
    }

    fun go2() = println("simple + $name")

    // 命名参数

    fun speak(name: String = "world", age: Int) = println("Hello " + name)

    val intArr = intArrayOf(1, 2, 3)
    fun test() {
        speak(age = 0)
        speak(age = 1, name = "")
        speak("", age = 1)

        intArr.forEach { i -> println("i : $i") }
    }

}