package com.improve.modules.kotlin.kaixue.chapter4

/**
 * Title: Kotlin中的泛型
 * <p>
 * Description:
 * </p>
 * @author Changbao
 * @date 2019/9/2  14:03
 */
class KotlinGenerics {
    /*
    练习题

    1.实现一个 fill 函数，传入一个 Array 和一个对象，将对象填充到 Array 中，要求 Array 参数的泛型支持逆变（假设 Array size 为 1）。

    2.实现一个 copy 函数，传入两个 Array 参数，将一个 Array 中的元素复制到另外个 Array 中，
    要求 Array 参数的泛型分别支持协变和逆变。（提示：Kotlin 中的 for 循环如果要用索引，需要使用 Array.indices）

     */

    fun <T : Monster> fill(arr: Array<in Animal>, monstor: T) {
        arr[0] = monstor
        println("fill = ${arr[0]}")
    }

    fun copy(arr1: Array<out Animal>, arr2: Array<in Monster>) {
        for (index in arr1.indices) {
            println("Array index = ${index}")

            //todo 2019年9月2日 15:45:35
//            arr1[index] = arr2[index]
//
//            arr2[index] = arr1[index]
        }
    }

    open class Animal constructor(val name: String) {
        constructor() : this("") {}
        constructor(name: String, type: Int) : this("动物") {}
    }

    //不写 constructor()  提示 : 没有主构造函数，无法启动父类型
    class Monster constructor(country: String) : Animal() {
        var country: String = ""

        constructor(country: String, type: Int) : this("怪物") {
            this.country = country
        }
    }

    // 包级可见的函数，接受一个字符串数组作为参数
    fun main(args: Array<String>) {
        val arr1 = arrayOfNulls<Animal>(1)
        arr1[0]=(Animal("动物1"))

        val arr2 = arrayOfNulls<Monster>(1)
        arr2[0]=(Monster("怪物1"))

        fill(arr1,Monster("🐵"))
        //TODO 2019年9月2日 15:45:43
//        copy(arr1,arr2)
    }

}