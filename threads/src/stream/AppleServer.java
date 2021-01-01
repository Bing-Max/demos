package stream;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AppleServer {

    private static List<Apple> appleList = new ArrayList<>();

    static {
        appleList.add(new Apple(1,"red",300,"湖南"));
        appleList.add(new Apple(2,"red",400,"天津"));
        appleList.add(new Apple(3, "green", 300, "湖北"));
        appleList.add(new Apple(4, "green", 500, "浙江"));
        appleList.add(new Apple(5, "green", 400, "山西"));
    }

    // 需求： 找出所有的red 苹果
    public void test1(){
        List<Apple> res = new ArrayList<>();

        for(Apple apple : appleList){
            if(apple.color.equals("red"))
                res.add(apple);
        }

        System.out.println(res);
    }

    public static void test2(){
        List<Apple> res = appleList.stream().filter(a -> a.color.equals("red")).collect(Collectors.toList());
        System.out.println(res);
    }

    // 增加条件
    public static void test3(){
        List<Apple> red = appleList.stream().filter(apple -> apple.color.equals("green")).filter(apple -> apple.weight > 300).collect(Collectors.toList());
        System.out.println(red);
    }

    public static void test4(Predicate<? super Apple> pr){
        List<Apple> collect = appleList.stream().filter(pr).collect(Collectors.toList());
        System.out.println(collect);

    }

    // 按照颜色求出苹果的权重平均
    public static void test5(){
        appleList.stream()
                .collect(Collectors.groupingBy(apple -> apple.color ,
                    Collectors.averagingInt(apple-> apple.weight))).
                forEach((k,v)-> System.out.println(k + ":" + v));
    }

    public static void test6(){
        appleList.stream().filter(a -> a.color.equals("red")||a.color.equals("green"))
                .map(a -> a.color) // 转化 从 Apple -> String
                .distinct() // 去重操作
                .peek(color-> System.out.println(color))// 什么都不做，只是执行一个函数
                .toArray();


    }

    public static void test7(){
        appleList.stream().filter(a -> a.color.equals("red")||a.color.equals("green"))
                .limit(2)
                .map(a -> a.weight)
                .sorted((c1,c2)-> c2.compareTo(c1))
                .forEach(System.out::println);

    }


    public static void main(String[] args) {
        test7();
    }


}
