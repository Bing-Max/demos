package study.col;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListTest {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        list.add("a");
        list.add("b");
        list.add("c");


//        test2(list);
//        test3(list);
//        test1(list);
    }

    public static void test1(List<String> list){
        for(String s : list){
            if(s.equals("a"))
                list.remove(s);
        }

        System.out.println(list);
    }

    public static void test2(List<String> list){
        Iterator<String> iterator = list.iterator();
        for(;iterator.hasNext();){
            String s = iterator.next();
            if (s.equals("a"))
                iterator.remove();
        }

        System.out.println(list);
    }

    public static void test3(List<String> list){
        for(int i = 0; i < list.size(); i++){
            String s = list.get(i);
            if(s.equals("a")){
                list.remove("a");
                i--;
            }
        }

        System.out.println(list);
    }
}
