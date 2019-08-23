package com.improve.modules.kotlin.clazz

import com.improve.modules.kotlin.basic.line

/**
 * 类与继承 https://www.kotlincn.net/docs/reference/classes.html
 *
 * 在 Kotlin 中的一个类可以有一个主构造函数以及一个或多个次构造函数
 *
 * 注意 Kotlin 并没有 new 关键字
 */
class InitOrderDemo(name: String) {
    val firstProperty = "First property: $name".also(::println)

    init {
        println("First initializer block that prints ${name}")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }
}

fun main(args: Array<String>) {
    InitOrderDemo("kotlin")
    line()

    Person("小灰灰", Person("灰太狼"))
    line()

    Constructors(1)
    line()

    DontCreateMe("小宝")
    line()

    UserManager() //带有默认值的主构造函数
    line()

    伴生对象.eat()
    伴生对象.Fly.jump()
}

class 伴生对象 {
    companion object Named {
        //每个类中只能有一个！！！
        fun eat() {
            println("eat")
        }

        fun run() {
            println("run")
        }
    }

    object Fly {
        fun jump() {
            println("jump")
        }
    }
}

//请注意，即使伴生对象的成员看起来像其他语言的静态成员，在运行时他们仍然是真实对象的实例成员，而且还可以实现接口：
interface Factory<T> {
    fun create(): T
}

interface Factory2<T> {
    fun destroy(): T
}

class MyClass {
    companion object : Factory<MyClass>,
        Factory2<MyClass> {
        private val myClass: MyClass = MyClass()

        override fun destroy(): MyClass {
            return myClass
        }

        override fun create(): MyClass {
            return myClass
        }
    }
}
val f2: Factory2<MyClass> = MyClass


// 如果构造函数有注解或可见性修饰符，这个 constructor 关键字是必需的，并且这些修饰符在它前面：
class Customer public @SuppressWarnings constructor(name: String) {}

// 如果类有一个主构造函数，每个次构造函数需要委托给主构造函数， 可以直接委托或者通过别的次构造函数间接委托。
// 委托到同一个类的另一个构造函数用 this 关键字即可：
class Person(val name: String) {
    constructor(name: String, parent: Person) : this(name) {
        //parent.children.add(this)
        println("Name : $name , Parent Name : ${parent.name}")
    }
}

// 请注意，初始化块中的代码实际上会成为主构造函数的一部分。
// 委托给主构造函数会作为次构造函数的第一条语句，因此所有初始化块中的代码都会在次构造函数体之前执行。
// 即使该类没有主构造函数，这种委托仍会隐式发生，并且仍会执行初始化块：
class Constructors {
    init {
        println("Init block Constructors")
    }

    constructor(i: Int) {
        println("Constructors")
    }
}

//私有无参构造器
class DontCreateMe private constructor() {
    init {
        println("Init block DontCreateMe")
    }

    constructor(name: String) : this() {
        println("DontCreateMe $name")
    }
}

/*
限定的 This
要访问来自外部作用域的this（一个类 或者扩展函数， 或者带标签的带有接收者的函数字面值）我们使用this@label，
其中 @label 是一个代指 this 来源的标签：
*/
class A { // 隐式标签 @A
    inner class B { // 隐式标签 @B
        fun Int.foo() { // 隐式标签 @foo
            val a = this@A // A 的 this
            val b = this@B // B 的 this

            val c = this // foo() 的接收者，一个 Int
            val c1 = this@foo // foo() 的接收者，一个 Int

            val funLit = lambda@ fun String.() {
                val d = this // funLit 的接收者
            }

            val funLit2 = { s: String ->
                // foo() 的接收者，因为它包含的 lambda 表达式
                // 没有任何接收者
                val d1 = this
            }
        }
    }
}

//注意：在 JVM 上，如果主构造函数的所有的参数都有默认值，编译器会生成一个额外的无参构造函数，它将使用默认值。
//这使得 Kotlin 更易于使用像 Jackson 或者 JPA 这样的通过无参构造函数创建类的实例的库。
class UserManager(val userName: String = "")

//嵌套类 - 不持有外部类的引用
class ClassOuter {
    private val bar: Int = 1

    class Nested {
        fun foo() = 2
    }
}

val demo = ClassOuter.Nested().foo() // == 2

//内部类 - 类可以标记为 inner 以便能够访问外部类的成员。内部类会带有一个对外部类的对象的引用：
class ClassInner {
    private val bar: Int = 1

    inner class Nested {
        fun foo() = 2
        fun go() = bar
    }
}

//匿名内部类   使用对象表达式创建匿名内部类实例：
//window.addMouseListener(object : MouseAdapter() {
//    ​
//    override fun mouseClicked(e: MouseEvent) { …… }
//    ​
//    override fun mouseEntered(e: MouseEvent) { …… }
//})

//对象表达式与对象声明
//https://www.kotlincn.net/docs/reference/object-declarations.html
//如果超类型有一个构造函数，则必须传递适当的构造函数参数给它。 多个超类型可以由跟在冒号后面的逗号分隔的列表指定：
open class ViewParent(canvas: String) {
    open fun draw() {} //默认final，加上open后可被覆写
}

interface OnClickListener {
    fun onClick()
    fun onTouch()
}

//🌶💩💉💧🐂🍺
fun test() {
    var superType: ViewParent = object : ViewParent("canvas"),
        OnClickListener {

        override fun draw() {
            super.draw()
        }

        override fun onClick() {

        }

        override fun onTouch() {
        }

    }
}

//任何时候，如果我们只需要“一个对象而已”，并不需要特殊超类型，那么我们可以简单地写：
fun foo() {
    val adHoc = object {
        var x: Int = 0
        var y: Int = 0
    }
    print(adHoc.x + adHoc.y)
}

// 请注意，匿名对象可以用作只在本地和私有作用域中声明的类型。如果你使用匿名对象作为公有函数的返回类型或者用作公有属性的类型，
// 那么该函数或属性的实际类型会是匿名对象声明的超类型，
// 如果你没有声明任何超类型，就会是 Any。在匿名对象中添加的成员将无法访问。
class C {
    // 私有函数，所以其返回类型是匿名对象类型
    private fun foo() = object {
        val x: String = "x"
    }

    // 公有函数，所以其返回类型是 Any
    fun publicFoo() = object {
        val x: String = "x"
    }

    fun bar() {
        val x1 = foo().x         // 没问题
        //val x2 = publicFoo().x          // 错误：未能解析的引用“x”  ????
    }
}

//注意：Any 并不是 java.lang.Object；尤其是，它除了 equals()、hashCode() 与 toString() 外没有任何成员。

