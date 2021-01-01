package study.func;

import java.util.function.Predicate;

public class Demo2 {
    public static void main(String[] args) {
        Predicate predicate = obj -> {return null != obj;};
        System.out.println(predicate.test(new Object()));
    }
}
