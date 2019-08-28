package com.improve.modules.kotlin.kaixue.chapter3

/**
 * Title: KaiXue3
 * <p>
 * Description:
 * </p>
 * @author Changbao
 * @date 2019/8/26  16:36
 */
class Person private constructor(var name: String) {//「主构造器 primary constructor」

    constructor(name: String, age: Int) : this(name) {

    }

    constructor(name: String, age: Int, isFamale: Boolean) : this(name, age) {

    }

}