### Java 功底

#### 计算机语言的发展过程
![](.README_images\计算机语言的发展过程.png)
<br>
**Java是一种半编译半解释型的语言。**
#### Java语言特点
1.简单、高效；<br>
2.面向对象；<br>
3.跨平台性（一次编译、到处运行）；<br>
![](.README_images\跨平台性.png)
<br>
4.交互式特性；<br>
5.多线程；<br>
6.动态的内存管理；<br>
7.安全性<br>

#### 二、Java 标识符、字面量、数据类型
- 标识符<br>
![](.README_images\标识符.png)<br>
- 分隔符<br>
![](.README_images\分隔符.png)<br>
- 关键字<br>
![](.README_images\关键字.png)<br>
- Java的命名规范<br>
![](.README_images\命名规范.png)<br>
- 注释<br>
![](.README_images\注释.png)<br>
- 变量、常量、字面量<br>
1.什么是变量？ -》 内存里的一段存储单元，有类型、名称和作用域，我们可以通过操作变量名进而操作相应的内存单元。<br>
2.变量的声明  -》 两种方式：类型 变量名称；类型 变量名称 = 值<br>
3.变量的初始化和赋值<br>
4.变量的分类和作用域<br>
局部变量<br>
实例变量 -- 成员变量<br>
类（静态）变量 static<br>
5.常量 -》 特殊的变量，不变的变量  ； 由 final 修饰，一旦赋值，不能修改 ； 全部大写<br>
6.字面量 ： 给常量或变量赋的值就叫字面量。<br>
- 数据类型<br>
ASCII码是 8位的，用于表示一些英文字母和特殊字符还是可以的，但是如果是汉字或是韩文、日文就不够用了，所以Java用的编码为UNICODE编码，
UNICODE编码是双字节的，16位 -- 即 char 16位<br>
float a=3.14F;// 默认是double类型，需要在3.14后面加上F或f标明它是Float类型<br>
![](.README_images\数据类型.png)<br>
数据类型取值范围计算：<br>
![](.README_images\数据类型取值范围计算.png)<br>
![](.README_images\数据类型取值范围.png)<br>
补充：char类型取值范围 ：0 - 65535 <br>
数据类型字面量<br>
![](.README_images\数据类型字面量.png)<br>
```
int a2 = 0b11011010; // 二进制  0b 或 0B
int a8 = 01213;      // 八进制
int a16 = 0x11fa901; // 十六进制  0x或 0X
```
类型转换 -- 涉及到内存中的【对位】问题，比如说：byte和int相加<br>
![](.README_images\类型转换.png)<br>
1.布尔类型不能和其他7中数据类型进行相互转换<br>
2.向上转型的顺序：byte -> (short = char) -> int -> long -> float -> double
向下转型：有可能溢出 int j=200;<br> ![](.README_images\数据类型向下转型.png)<br>
输出为 -50<br>
如果是 int j=20; 此时byte（水杯大小为-128~127）就放得下，输出正常20！！！<br>
例2：<br>
![](.README_images\数据类型转换2.png)<br>
输出为3153 。 精度损失，小数位没有了<br>

#### 三、Java运算符和表达式
![](.README_images\Java运算符和表达式.png)<br>
- 赋值运算<br>
![](.README_images\赋值运算.png)<br>
![](.README_images\赋值运算-数组[重点].png)<br>
其中，int[] array2=array1; 赋给的是地址（指针）的传递<br>
![](.README_images\赋值运算-对象[重点].png)<br>
- 算术运算<br>
注意的点：<br>
1./ 是求商的，int a=1/2; 结果为0 ； %是取余运算，int a=1%2; 结果为1。
2.char 进行算术运算时，会转换成 int 类型进行计算, eg:
```
char a = 'a';
char b = 'b';
int c = a + b;
System.out.println("c = "+c); // 输出为 c = 195
```
3.被除数不能是0 -- 抛出异常： java.lang.ArithmeticException: / by zero
4.+ 号在字符串拼接中的应用。
- 自增自减<br>
除了 int 类型以外，其他基本类型也可以使用自增自减 (除了boolean) ：
```
float f= 0.1F;
f++;
double d=3.14D;
d++;
char c = 'c';
c++;
System.out.println("f = "+f);
System.out.println("d = "+d);
System.out.println("c = "+c);
```
运行结果: -- 注意char类型，自增后的值对应的是ASCII码表中的数值！
```
f = 1.1
d = 4.140000000000001
c = d
```
- 关系运算<br>
也叫比较运算<br>
![](.README_images\关系运算.png)<br>
对象的比较：1.比较指针；2.比较内容 equals<br>
- 三目运算符<br>
https://ke.qq.com/webcourse/index.html#taid=743235500791473&vid=j1412m6a3ox&course_id=153265&term_id=100175081
<br>
<br>


#### 网络通信
> HTTP两种请求方式：get\post

