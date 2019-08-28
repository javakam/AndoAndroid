package com.improve.modules.kotlin.kaixue.chapter2

/**
 * Creating a singleton
 *
 * Kotlin object : https://blog.csdn.net/love667767/article/details/79426543
 */
// 👇 class 替换成了 object
object Singleton {
    val name = "Name"

    //不允许声明构造方法
    //constructor(){}

    fun instance() {
        //
    }
}