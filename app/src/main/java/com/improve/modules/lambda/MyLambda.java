package com.improve.modules.lambda;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * java8 lambda表达式学习
 * <p>
 * http://www.importnew.com/16436.html
 * <p>
 * Created by javakam on 2018/4/2.
 */
public class MyLambda {

    public static void main(String[] args) {
        demo3();
    }

    //1 lambda表达式实现Runnable
    private static void demo1() {
        new Thread(() -> System.out.println("hello lambda")).start();
    }

    //2 进行事件处理
    private static void demo2() {
//        TextView textView=new TextView(context);
//        textView.setOnClickListener(view-> {
//                    Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()});
    }

    //3 对列表进行迭代
    private static void demo3() {
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(n -> System.out.println(""));
        // 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
        // 看起来像C++的作用域解析运算符
        features.forEach(System.out::println);
    }

    //4 使用lambda表达式和函数式接口Predicate
    //Predicate非常适合用于做过滤
    private static void demo4() {
        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        filter(languages, (str) -> ((String) str).startsWith("J"));

        System.out.println("Languages which ends with a ");
        filter(languages, (str) -> str.toString().endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str) -> true);

        System.out.println("Print no language : ");
        filter(languages, (str) -> false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str) -> ((String) str).length() > 4);
    }

    private static void filter(List names, Predicate condition) {
        for (Object obj : names) {
            // 使用Predicate的test()方法判断该对象是否满足Predicate指定的条件
            if (condition.test(obj)) {
                System.out.println("" + obj);
            }
        }
        //更帅的操作
        names.stream().filter(n -> condition.test(n)).forEach(n -> System.out.println("" + n));
        // names.stream().filter(n -> condition.test(n)).forEach(System.out::println);

    }

    //5 如何在lambda表达式中加入Predicate
    private static void demo5() {
        // 甚至可以用and()、or()和xor()逻辑函数来合并Predicate，
        // 例如要找到所有以J开始，长度为四个字母的名字，你可以合并两个Predicate并传入
        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        Predicate<String> startWithJ = n -> n.toString().startsWith("J");
        Predicate<String> length4 = n -> n.toString().length() == 4;
        //and 输出：Java
        //languages.stream().filter(startWithJ.and(length4)).forEach(System.out::println);

        //or  输出：Java C++
        //Predicate<String> havePlusSignal = n -> n.toString().contains("+");
        //languages.stream().filter(startWithJ.or(havePlusSignal)).forEach(System.out::println);

        //negate反面  输出： Scala  C++  Haskell Lisp
        languages.stream().filter(startWithJ.negate()).forEach(System.out::println);

    }

    //6.1 使用lambda表达式的Map和Reduce示例 - Map对象转换
    private static void demo6_1() {
        // 不使用lambda表达式为每个订单加上12%的税
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        for (Integer cost : costBeforeTax) {
            double price = cost + .12 * cost;
            System.out.println(price);
        }
        // 使用lambda 类似于RxJava中的map操作符，都是起到对象转换的作用
        costBeforeTax.stream().map(cost -> cost + 0.12 * cost).forEach(System.out::println);

    }

    //6.2 使用lambda表达式的Map和Reduce示例 - Reduce折叠操作
    //reduce() 函数可以将所有值合并成一个。eg：SQL中的 sum()、avg() 或者 count() 的聚集函数，实际上就是 reduce 操作，因为它们接收多个值并返回一个值。
    private static void demo6_2() {
        // 为每个订单加上12%的税
        // 老方法：
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double total = 0;
        for (Integer cost : costBeforeTax) {
            double price = cost + .12 * cost;
            total = total + price;
        }
        System.out.println("total = " + total);

        double bill = costBeforeTax.stream().map(cost -> cost + .12 * cost).reduce((sum, price) -> sum + price).get();
        System.out.println("bill = " + bill);
    }

    //7 通过过滤创建一个String列表 -- filter
    private static void demo7() {
        List<String> strList = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        // 创建一个字符串列表，每个字符串长度大于2
        List<String> filtered = strList.stream().filter(x -> x.length() > 2).collect(Collectors.toList());
        System.out.printf("Original List : %s, filtered list : %s %n", strList, filtered);
    }

    //8 对列表的每个元素应用函数
    private static void demo8() {
        // 将字符串换成大写并用横线链接起来
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K", "Canada");
        String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining("-"));
        System.out.println(G7Countries);
    }

    //9 复制不同的值，创建一个子列表  -  distinct() 方法来对集合进行去重
    private static void demo9() {
        // 用所有不同的数字创建一个正方形列表
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> distinct = numbers.stream().map(i -> i * i).distinct().collect(Collectors.toList());
        System.out.printf("Original List : %s , Square Without duplicates : %s %n", numbers, distinct);
    }

    //10 计算集合元素的最大值、最小值、总和以及平均值
    //summaryStatistics()可以返回IntSummaryStatistics、LongSummaryStatistics 或者 DoubleSummaryStatistic，描述流中元素的各种摘要数据。
    private static void demo10() {
        //获取数字的个数、最小值、最大值、总和以及平均值
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());
    }

}
