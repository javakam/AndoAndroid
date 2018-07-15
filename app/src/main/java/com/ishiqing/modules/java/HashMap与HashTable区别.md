### HashMap与HashTable区别

1、父类继承
Hashtable继承自Dictionary类，而HashMap继承自AbstractMap类，但二者都实现了Map接口

2、安全性
hashMap为线程不安全，HashMap是Hashtable的轻量级实现（非线程安全的实现）。而hashtable是线程安全的，因为hashtable是经过synchronized修饰。

3、constains方法

HashMap把Hashtable的contains方法去掉了，改成containsValue和containsKey，因为contains方法容易让人引起误解。

Hashtable则保留了contains，containsValue和containsKey三个方法，其中contains和containsValue功能相同。

4、null
Hashtable中，key和value都不允许出现null值。

HashMap中，null可以作为键，这样的键只有一个；可以有一个或多个键所对应的值为null。当get()方法返回null值时，可能是 HashMap中没有该键，也可能使该键所对应的值为null。因此，在HashMap中不能由get()方法来判断HashMap中是否存在某个键， 而应该用containsKey()方法来判断。

5、扩容方式
Hashtable和HashMap它们两个内部实现方式的数组的初始大小和扩容的方式。HashTable中hash数组默认大小是11，增加的方式是 old*2+1。

HashMap中hash数组的默认大小是16，而且一定是2的指数。