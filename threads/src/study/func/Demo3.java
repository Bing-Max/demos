package study.func;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Demo3 {
    public static void main(String[] args) {
        Consumer consumer = str ->{
            System.out.println(str);
        };

        consumer.accept("hello");

        Supplier supplier = () ->{return 1024;};
        System.out.println(supplier.get());
    }
}
