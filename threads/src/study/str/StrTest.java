package study.str;

import java.util.HashMap;

public class StrTest {

    private final static String STR = "binghello";

    public static void main(String[] args) {


        String s = new StringBuilder().append("bing").append("hello").toString();

        System.out.println(s);
        System.out.println(s.intern());

        System.out.println(s == s.intern());

        String s2 = new StringBuilder().append("ali").append("baba").toString();
        System.out.println(s2);
        System.out.println(s2.intern());

        String s3 = "alibaba";
        System.out.println(s3 == s2);

        System.out.println(s2 == s2.intern());

        String s1 = new StringBuilder().append("ja").append("va").toString();
        System.out.println(s1);
        System.out.println(s1.intern());

        System.out.println(s1 == s1.intern());

        // true
        // true
        // false
    }
}
