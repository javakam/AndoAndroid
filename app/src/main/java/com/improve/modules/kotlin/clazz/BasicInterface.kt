package com.improve.modules.kotlin.clazz

import practice.kotlin.basic.line

/**
 *
 */
interface MyInterface {

    //在接口中声明的属性要么是抽象的，要么提供访问器的实现。
    //在接口中声明的属性不能有幕后字段（backing field），因此接口中声明的访问器不能引用它们。

    val prop: Int                           //1.抽象的
    val propertyWithImplementation: String  //2.提供访问器
        get() = "foo"
//        get() = field   ->   接口中的属性不能有幕后字段

    //fun bar()
    //fun bar2(){ //可选的方法体  }

    //接口中方法可以有方法体，类似于java8的特性
    fun foo() {
        println(prop)
        line()

        println(propertyWithImplementation)
    }

}

class Child : MyInterface {

    override val prop: Int = 29
    override val propertyWithImplementation: String
        get() = super.propertyWithImplementation

}

fun main(args: Array<String>) {
    val child = Child()
    child.foo()
    line()

    //扩展String类

}