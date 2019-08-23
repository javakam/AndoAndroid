package com.improve.modules.kotlin.clazz

import com.improve.modules.kotlin.basic.line

/**
 * 属性与字段
 *
 * https://www.kotlincn.net/docs/reference/properties.html
 */
private class BasicVariables {
    //如果你需要改变一个访问器的可见性或者对其注解，但是不需要改变默认的实现， 你可以定义访问器而不定义其实现:
    var setterVisibility: String = "abc"
        private set // 此 setter 是私有的并且有默认实现

    var setterWithAnnotation: Any? = null
        @SuppressWarnings set // 用 Inject 注解此 setter

    //幕后字段
    var isLoading: Boolean
        get() {
            println("isLoading 的幕后字段 get-value")
            return false
        }
        set(value) {
            println("isLoading 的幕后字段 set-value")
            isLoading = value
        }

    //幕后属性
    //如果你的需求不符合这套“隐式的幕后字段”方案，那么总可以使用 幕后属性（backing property）：
    private var _table: Map<String, Int>? = null
    public val table: Map<String, Int>
        get() {
            println("幕后属性 get")
            if (_table == null) {
                _table = HashMap() // 类型参数已推断出
            }
            return _table ?: throw AssertionError("Set to null by another thread")
        }
    //从各方面看，这正是与 Java 相同的方式。因为通过默认 getter 和 setter 访问私有属性会被优化，所以不会引入函数调用开销

    /*
    延迟初始化属性与变量

    一般地，属性声明为非空类型必须在构造函数中初始化。 然而，这经常不方便。
    例如：属性可以通过依赖注入来初始化， 或者在单元测试的 setup 方法中初始化。
    这种情况下，你不能在构造函数内提供一个非空初始器。 但你仍然想在类体中引用该属性时避免空检查。

    该修饰符只能用于在类体中的属性（不是在主构造函数中声明的 var 属性，
    并且仅当该属性没有自定义 getter 或 setter 时），而自 Kotlin 1.2 起，也用于顶层属性与局部变量。
    该属性或变量必须为非空类型，并且不能是原生类型。

    在初始化前访问一个 lateinit 属性会抛出一个特定异常，该异常明确标识该属性被访问及它没有初始化的事实。
     */
    //var ssk:String
    lateinit var ssl: String
    /*
    检测一个 lateinit var 是否已初始化（自 1.2 起）
    要检测一个 lateinit var 是否已经初始化过，请在该属性的引用上使用 .isInitialized：

    if (foo::bar.isInitialized) {
        println(foo.bar)
    }
    此检测仅对可词法级访问的属性可用，即声明位于同一个类型内、位于其中一个外围类型中或者位于相同文件的顶层的属性。
     */

    //属性覆盖
    //你也可以用一个 var 属性覆盖一个 val 属性，但反之则不行。
    //这是允许的，因为一个 val 属性本质上声明了一个 getter 方法，而将其覆盖为 var 只是在子类中额外声明一个 setter 方法。

    //请注意，你可以在主构造函数中使用 override 关键字作为属性声明的一部分。
    interface Foo {
        val count: Int
    }

    class Bar1(override val count: Int) : Foo
    class Bar2 : Foo {
        override var count: Int = 0
    }
}

fun main(args: Array<String>) {
    val b = BasicVariables()
    //StackOverFlow 堆栈溢出异常！！！ ？？？
//    b.isLoading=true
    println("isLoading = ${b.isLoading}")
    line()

    val map = b.table
    println("map ${map.toString()}")
    line()


}