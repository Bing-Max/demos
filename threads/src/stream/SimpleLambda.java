package stream;

import study.Interrupt;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SimpleLambda {
    public static void main(String[] args) {

        new Thread(()-> System.out.println("hello world")).start();

        int[] nums = {1,2,3,4,5};

        List<Integer> collect = IntStream.of(nums).boxed().collect(Collectors.toList());
        System.out.println(collect);

        long[] longs = {1,2,3,4,5};
        String[] strings = {"ni","hao"};
//        Character[] chars = {'a','b','c'};
        char[] chars = {'a','b','c'};
        List<Character> collect6 = new String(chars).chars().mapToObj(i -> (char) i).collect(Collectors.toList());



        Boolean[] booleans = {true, false,true};


        List<Integer> collect2 = Arrays.stream(nums).boxed().filter(a-> a>3).collect(Collectors.toList());

        List<Long> collect1 = Arrays.stream(longs).boxed().collect(Collectors.toList());

        List<String> collect3 = Arrays.stream(strings).collect(Collectors.toList());

        Object[] objects = collect.stream().toArray();
        System.out.println(objects);

//        List<Character> collect4 = Arrays.stream(chars).collect(Collectors.toList());
        List<Boolean> collect5 = Arrays.stream(booleans).collect(Collectors.toList());
        System.out.println(collect5);
//        System.out.println(collect4);

//        List<Double> collect6 = Arrays.stream(new double[]{0.25, 0.125}).boxed().collect(Collectors.toList());

//        System.out.println(collect2);
//        System.out.println(collect1);
//        System.out.println(collect3);

//        int[][] arr = {
//                {1,2},
//                {2,3},
//                {3,4},
//                {2,4}
//        };
        int[][] arr = {{-2147483646,-2147483645},{2147483646,2147483647}};
        Arrays.sort(arr, (arr1, arr2) -> arr1[0] > arr2[0]? 1 : -1);
        for(int[] ar : arr){
            System.out.println(Arrays.toString(ar));
        }

        System.out.println(Integer.MIN_VALUE < Integer.MAX_VALUE);
    }
}
