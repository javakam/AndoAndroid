package com.improve.modules.kotlin.basic

/**
 * Kotlin 面向对象 & play with lambda
 */
fun main(args: Array<String>) {
    var user1: User = User("小明", "1772664927@qq.com")
    var user2: User = User("小红", "123222557@qq.com")
    var user3: User = User("小红", "123222557@qq.com")
    println(user1.name + "  " + user2.name)
    println(
        "HashCode : ${user1.hashCode()} + \"   \" + ${user2.hashCode()}"
                + " \rEquals : ${user1.equals(user2)}" //or " \rEquals : " + customer1.equals(customer2)
    )

    user1.age = 6;
    user2.age = 18;
    user3.age = 28;
    println("${user2.equals(user3)}")
    println("${user2 == user3}")

    //copy
    val jack = User(name = "Jack", email = "123456@qq.com")
    val newJack = jack.copy(email = "654321@qq.com")
    println("${jack.toString()}+\"  \"+${newJack.toString()}")
    val copyJack = jack.copy("JackCopy", "123456@qq.com", 19)
    println(jack == copyJack)
    println("------------------------------------")

    //lambda
    val users = listOf<User>(user1, user2, user3)
    //users.filter { it -> it.age > 16 }.forEach { println(it.toString()) }
    //even shorter :
    users.filter { it.age > 16 }.forEach { println(it.toString()) }

}

/**
 * data class 数据存储类
 *
 * 构造器中的name、email会用于 toString(), equals(), hashCode(), and copy() ，age不会！！！
 * 覆写equals方法会更改对比规则，而覆写hashCode方法则不会有影响
 */
data class User(val name: String, var email: String) {
    var age: Int = 1;

    //默认的copy方法是同构造器一样的两个参数
    fun copy(name: String = this.name, email: String = this.email, age: Int = this.age) =
        User(name, email)

    override fun toString(): String {
        return "User(name=$name, email=${this.email}, age=$age)"
    }

}