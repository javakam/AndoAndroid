package com.improve.modules.kotlin.clazz

import kotlin.reflect.KProperty

/**
 * 委托属性
 *
 * https://www.kotlincn.net/docs/reference/delegated-properties.html
 */
class PropertyDelegate : Any() {

    var prop: String by MyDelegate()

    //延迟属性 lazy
    lateinit var str: String
    val myLazy: Int by lazy {
        println("lazy ... ")
        2333
    }

}

class MyDelegate {
    operator fun getValue(delegate: Any, kProperty: KProperty<*>): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    operator fun setValue(delegate: Any, kProperty: KProperty<*>, s: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

fun main(args: Array<String>) {

}