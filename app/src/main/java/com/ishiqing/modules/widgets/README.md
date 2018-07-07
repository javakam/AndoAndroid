### FlexboxLayout
>   开发中，不推荐直接使用 **FlexboxLayout** ，而是使用 **【RecyclerView + FlexboxLayoutManager】** 的方案，
这样一来既支持View回收，又能实现Flexbox的效果，二者兼得。(当然，目前 FlexboxLayout 只是1.0.0版本，
以后是否会推出更强大的功能也是未知呢)

-   基于 FlexboxLayout 原生的单选多选事件参考 ：https://blog.csdn.net/LXLYHM/article/details/78753558  <br>
不过还是推荐用 **【RecyclerView + FlexboxLayoutManager】** 去实现。