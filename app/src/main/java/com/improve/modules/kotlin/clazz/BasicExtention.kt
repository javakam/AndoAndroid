package com.improve.modules.kotlin.clazz

import practice.kotlin.basic.line

//扩展函数 https://www.kotlincn.net/docs/reference/extensions.html

//注意：由于扩展没有实际的将成员插入类中，因此对扩展属性来说幕后字段是无效的。
//这就是为什么扩展属性不能有初始化器。他们的行为只能由显式提供的 getters/setters 定义。
class Extension {
    //扩展属性
    val <T> List<T>.lastIndex: Int
        get() = size - 1

    val String.test: String
        get() = "test"

    //扩展方法
    fun String?.toNoNullString(): String? = this?.replace("null", "") ?: ""

    fun test() {
        "河北省null滦平县".toNoNullString()
        "".test
    }
}

class DD {
    fun bar() {
        println("D bar")
    }

    fun go() {
        println("D go")
    }
}

class CC {
    fun baz() {
        println("C baz")
    }

    fun DD.foo() {
        bar()       // 调用 D.bar
        baz()       // 调用 C.baz
    }

    fun DD.go() {
        println("C go")
        go()
    }

    fun caller(d: DD) {
        d.foo()     // 调用扩展函数
        d.go()      // 不会覆盖

    }
}

typealias Predicate<T> = (T) -> Boolean

fun foo(p: Predicate<Int>) = p(-8)

fun main() {
    var cc: CC = CC()
    cc.caller(DD())
    line()

    mutableMapOf<Int, Int>(0 to 8, 1 to 16, 2 to 28).forEach { println(it.toString()) }
    line()

    //泛型扩展   一切都可以调用该方法
    fun <T> T.basicToString(): String {  // 扩展函数
        println("xxxxxx")
        return "";
    }
    println(2.basicToString())
    line()

    /*
    ???
     */
    //val sx: Predicate<Int> = { i: Int -> i > 2 }
    val f: (Int) -> Boolean = { it > 0 }
    println(foo(f))                     // 输出 "true"
    val p: Predicate<Int> = { it > 0 }
    println(listOf(1, -2).filter(p))    // 输出 "[1]"

}