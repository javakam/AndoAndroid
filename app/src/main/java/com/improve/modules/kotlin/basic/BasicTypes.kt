package com.improve.modules.kotlin.basic

fun main(args: Array<String>) {

    //注意字符不是Kotlin中的数字
    var charVal: Char = 'A'
    println(charVal)  //输出 A

    //注意：不支持八进制文字

    //使用下划线使数字更具可读性
    var oneMillion = 1_000_000
    val creditCardNumber = 1234_5678_9012_3456L
    val socialSecurityNumber = 999_99_9999L
    val hexBytes = 0xFF_EC_DE_5E
    val bytes = 0b11010010_01101001_10010100_10010010
    line()

    //表示方式  拆装箱问题 & ==和===的区别
    表示方式()
    显式转换()
    charAndString()


    //条件表达式   Kotlin中没有三目运算符
    // 传统用法
    var a: Int = 6
    var b: Int = 8
    var max1 = a
    if (a < b) max1 = b

    // With else
    var max2: Int
    if (a > b) {
        max2 = a
    } else {
        max2 = b
    }

    // 作为表达式  【正确姿势】
    val max3 = if (a > b) a else b
    //if的分支可以是代码块，最后的表达式作为该块的值：
    val max = if (a > b) {
        print("Choose a")
        a
    } else {
        print("Choose b")
        b
    }

    //When 语法
    //Since Kotlin 1.3, it is possible to capture when subject in a variable using following syntax:

    /*fun Request.getBody() =
        when (val response = executeRequest()) {
            is Success -> response.body
            is HttpError -> throw HttpException(response.status)
        }
     */





}

fun charAndString() {
    //Char 字符用 Char 类型表示。它们不能直接当作数字
    var char_m: Char = 'm'
    println("Char $char_m 对应ASCII码表对应的数值为 ${char_m.toInt()}") //显式转换为数字

    //Array
    // 通过 arrayOf(1, 2, 3) 创建了 array [1, 2, 3]。
    // 或者，库函数 arrayOfNulls() 可以用于创建一个指定大小的、所有元素都为空的数组
    var arr = arrayOf(1, true, "haha", 'm')
    var arr2 = arrayOfNulls<Any>(10)

    // 创建一个 Array<String> 初始化为 ["0", "1", "4", "9", "16"]
    val asc = Array<String>(6, { i -> (i * i).toString() })
    asc.forEach { println(it) }
    line()

    /*
    注意: 与 Java 不同的是，Kotlin 中数组是不型变的（invariant）。这意味着 Kotlin 不让我们把 Array<String> 赋值给 Array<Any>，
    以防止可能的运行时失败（但是你可以使用 Array<out Any>, 参见类型投影 https://www.kotlincn.net/docs/reference/generics.html#%E7%B1%BB%E5%9E%8B%E6%8A%95%E5%BD%B1）。
     */

    //String
    var str: String = "kotlin"
    //访问 str 中的每个字符串
    println(" str 2 : " + str[2])
    for (c in str) {
        println("$c ，")
    }
    println("${'$'}998")
    line()

    //原始字符串 使用三个引号（"""）分界符括起来，内部没有转义并且可以包含换行以及任何其他字符:
    val text = """
    for (c in "foo")
        print(c)
    """
    println(text)

    //通过 trimMargin() 函数去除前导空格：
    //默认 | 用作边界前缀，但你可以选择其他字符并作为参数传入，比如 trimMargin(">")。
    val text2 = """
    |Tell me and I forget.
    |Teach me and I remember.
    |Involve me and I learn.
    |(Benjamin Franklin)
    """.trimMargin()
    println(text2)

}

fun 表示方式() {

    /*
    注意: 在 java 平台上，数值被 JVM 虚拟机以字节码的方式物理存储的，除非我们需要做可空标识(比如说 Int?) 或者涉及泛型。
    后者中数值是装箱的(参考http://www.kotlindoc.cn/Basics/Basic-Types.html)
    数据的装箱不具备同一性，但具有相等性！
    val a:Int =1  -> int a=1
    val a:Int?=1  -> Integer a=1

    ==比较的是值，类似于Java中的equals方法；===比较的地址
    */
    val a: Int = 10000
    println(a === a) // Prints 'true'
    val boxedA: Int? = a
    val anotherBoxedA: Int? = a
    println(boxedA === anotherBoxedA) // !!!Prints 'false'!!!

    //考虑到 IntegerCache -128~127 闭区间不会拆装箱
    val b: Int = 127
    println(b === b) // Prints 'true'
    val boxedB: Int? = b
    val anotherBoxedB: Int? = b
    println(anotherBoxedB === boxedB)
}

fun 显式转换() {
    //假想的代码，实际上并不能编译：
//    val aa: Int? = 1       // 一个装箱的 Int (java.lang.Integer)
//    val bb: Long? = aa     // 隐式转换产生一个装箱的 Long (java.lang.Long)
//    print(bb == aa)        // 这将输出“false”鉴于 Long 的 equals() 会检测另一个是否也为 Long

    //因此较小的类型不能隐式转换为较大的类型。 这意味着在不进行显式转换的情况下我们不能把 Byte 型值赋给一个 Int 变量。

    val b: Byte = 1         // OK, 字面值是静态检测的
    //val i: Int = b        // 错误
    val i: Int = b.toInt()  // OK：显式拓宽数字
    println(i)
    //缺乏隐式类型转换很少会引起注意，因为类型会从上下文推断出来，而算术运算会有重载做适当转换，例如：
    val l = 1L + 3    // Long + Int => Long

    if (-0.0 < 0.0) {
        println("-0.0 < 0.0")
    }
    line()

}