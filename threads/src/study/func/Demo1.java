package study.func;

import java.util.function.Function;

public class Demo1 {

    public static void main(String[] args) {
        Function func = str ->{return str;};

        System.out.println(func.apply("hello"));
    }
}
