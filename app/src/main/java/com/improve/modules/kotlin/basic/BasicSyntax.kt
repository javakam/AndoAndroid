package com.improve.modules.kotlin.basic

import java.nio.file.Files
import java.nio.file.Paths

/**
 * Title: BasicSyntax
 * <p>
 * Description: Kotlin 基本语法
 * </p>
 * @author Changbao
 * @date 2019-03-05  12:17
 */
class BasicSyntax : Object() {
}

fun line(): Unit {
    println("------------------------------------------")
}

fun main() {

    println("sum1 result 1 : " + sum1(1, 2))
    println("sum1 result 2 : ${sum1(1, 5)}")
    println("sum2 result : ${sum2(1, 2)}")
    sum3(1, 2)
    line()

    //定义变量、常量d
    val a: Int = 1;
    val aa = 1;
    var b = 2;
    b = 3;
    val c: Int
    c = 1;//常量必须初始化
    println("变量、常量 : c  =  $c")

    //
    variable()
    println("1 和 2 谁大 ？ ${maxOf(1, 2)}")
    //instanceof  == is
    if (2.0 is Double) {
        println("instanceof is replaced by keyword 'is'")
    }
    line()

    //for list & map
    println("输出String集合中的每个元素")
    forList()
    println("输出Map中的Pair")
    val map: Map<String, Int> = mapOf(Pair("Apple", 10), Pair("Banana", 820), Pair("Orange", 1920))
    //val mutableMap: MutableMap<String, Int> = mutableMapOf(...)
    forMap(map)

    //when
    println(whenOpt(998))
    //range
    /*
    range 所有情况总结
    for (i in 1..100) { ... }  // closed range: includes 100
    for (i in 1 until 100) { ... } // half-open range: does not include 100
    for (x in 2..10 step 2) { ... }
    for (x in 10 downTo 1) { ... }

    if (x in 1..10) { ... }
     */
    rangeOpt()
    //range downTo & step option
    rangeOpt1()
    line()

    //声明方法时给默认值，给了默认值后再有调用foo则不用传参了
    practice.kotlin.clazz.foo()

    //lazy
    val p: String by lazy {
        // compute the string
        return@lazy "Hello World"
    }
    println("lazy : $p")

    //Extension Functions
    println("My name is kotlin".replaceSpaceWithCustomLine("|"))

    //Creating a singleton  ->  @see practice.kotlin.Singleton
    line()

    /*
    ?=  ?. ?: !!. 区别
     */
    //var name: String = null      //Error:Null can not be a value of a non-null type String
    //var name1: String? = null    //可空类型，可以赋值为 null
    var name: String = "kotlin"
    println(name.length)           //正常使用即可，因为是非空类型，可以放心使用，不用判断 if(name != null)

    var name1: String? = null      //可空类型，可以赋值为 null
    //这么调用时错误的
//   println(name1.length)
//   Error:Only safe (?.) or non-null asserted (!!.) calls are allowed on a nullable receiver of type String?

    //Java用法
    if (name1 != null) {
        //println(name1.length)
    }
    //Kotlin用法
    println(name1?.length)         //当name1是null时，会输出null
    //或者这么使用
    //println(name1!!.length)      //当name1是null是报npe错误
    //eg: 变量为布尔值并且是可空类型时
    val isLoading: Boolean? = null
    if (isLoading == true) {
        // ...
    } else {
        // `b` is false or null
    }

    //?: 当左边为空时执行右边的代码
    var str: String? = null
    println(str ?: "str null!!!")
    //println(str ?: throw NullPointerException("空指针异常！！！"))
    line()

    /*
    trycatch / if
     */
    tryOpt()
    ifOpt(2)
    line()

    val filePath = "/Users/TANGRONG/fastwork/IdeaProjects/src/practice/kotlin/静夜思.txt"
    //读取本地文件中的数据
    val stream = Files.newInputStream(Paths.get(filePath))
    stream.buffered().reader().use { reader ->
        println(reader.readText())
    }
    line()
    //或者
    val lines = Files.newBufferedReader(Paths.get(filePath)).buffered().readText()
    println(lines)
}

fun foo(a: Int = 0, b: String = "") {}

fun sum1(a: Int, b: Int): Int? {
    return a + b;
}

fun sum2(a: Int, b: Int) = a + b

fun sum3(a: Int, b: Int): Unit {
    println("sum3 result : $a plus $b = ${a + b}")
}

fun variable() {
    var a = 1
    val s1 = "a is $a"

    a = 2
    val s2 = "${s1.replace("is", "was")}, but now is $a"
    println(s2)
}

fun maxOf(a: Int, b: Int) = if (a > b) a else b

fun whenOpt(obj: Any): String = when (obj) {
    is Boolean -> "It is a Boolean : $obj"
    1 -> "One"
    "Hello" -> "Greeting"
    is Long -> "Long"
    in listOf<Any>(2333, 666, 998) -> "Niu Pi : $obj"
    !is String -> "Not a string"
    else -> "Unknown"
}

/**
 * 输出 String 集合每个元素
 */
fun forList() {
    val arrayListName = listOf<String>("aaa", "bbb", "ccc")

    //最简单的循环
    println("lambda实现for循环")
    arrayListName.forEach { print("$it .") }
    println()

    for (item in arrayListName) {
        print("$item ,")
    }
    for (item in arrayListName.listIterator()) {
        println(item)
    }

    //索引遍历
    for (index in arrayListName.indices) {
        println("Value at $index is ${arrayListName[index]}")
    }
    //或者你可以用库函数 withIndex：
    for ((index, value) in arrayListName.withIndex()) {
        println("Pair is : $index -> $value")
    }
}

fun forMap(map: Map<String, Int>) {
    for ((k, v) in map) {
        println("$k -> $v")
    }
}

fun rangeOpt() {
    val list = listOf("a", "b", "c")
    if (-1 !in 0..list.lastIndex) {
        println("-1 is out of range")
    }
    println("list.size is ${list.size} & list.indices are " + list.indices)

    if (list.size !in list.indices) {
        println("list size is out of valid list indices range, too")
    }
}

fun rangeOpt1() {
    for (i in 1 until 10) {
        // i in [1, 10), 10 is excluded
        print("$i ")
    }
    println()
    for (x in 1..10 step 2) {
        print(x)
    }
    println()
    for (x in 9 downTo 3 step 3) {
        print(x)
    }
    println()
}

private fun String.replaceSpaceWithCustomLine(s: String): String {
    return replace(" ", s)
}

//'try/catch' expression
fun tryOpt() {
    val result = try {
        2333
    } catch (e: ArithmeticException) {
        throw IllegalStateException(e)
    }

    // Working with result
    println("tryOpt $result")
}

//'if' expression
fun ifOpt(param: Int) {
    val result = if (param == 1) {
        "one"
    } else if (param == 2) {
        "two"
    } else {
        "three"
    }
    println("ifOpt $result")
}