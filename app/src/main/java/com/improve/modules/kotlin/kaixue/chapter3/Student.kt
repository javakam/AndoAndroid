package com.improve.modules.kotlin.kaixue.chapter3

/**
 * Title: Student$
 * <p>
 * Description:
 * </p>
 * @author Changbao
 * @date 2019/8/28  14:43
 */
class Student constructor(val name: String, val age: Int, val isMale: Boolean) {

    constructor(age: Int) : this(isMale = true, name = "", age = age) {
    }

    fun show() = println("属性字段: $name $age $isMale")

    //找出集合 {21, 40, 11, 33, 78} 中能够被 3 整除的所有元素，并输出。
    fun listShow() {
        val list = listOf(21, 40, 11, 33, 78)
        list.filter { i ->
            i % 3 == 0
        }.forEach { println(it) }
    }
}