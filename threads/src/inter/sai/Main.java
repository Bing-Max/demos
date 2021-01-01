package inter.sai;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static long[] facs;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m, n;
        int[] candies;
        while (sc.hasNextInt()){
            n = sc.nextInt();
            m = sc.nextInt();

            candies = new int[n];
            for (int i = 0; i < n; i++) {
                candies[i] = sc.nextInt();
            }

            System.out.println(getAllCount(candies, m));

        }

    }

    public static int getAllCount(int[] candies, int m){

        // count all colors candies
        Map<Integer, Long> collect = Arrays.stream(candies).boxed().collect(Collectors.toList())
                .stream().collect(Collectors.groupingBy(integer -> integer, Collectors.counting()));


        Long max = collect.values().stream().parallel().max((l1, l2) -> {
            return l1.compareTo(l2);
        }).get();

        int size = collect.values().stream().filter(aLong -> aLong > 1).distinct().collect(Collectors.toList()).size();

        facs = new long[(int) (max + 1)];

        long temp = (m - max > 1)? 1 : (m - max);
        final long factor = size + temp;
        final long[] res = {1L};

        collect.values().stream().forEach(count ->{
            if(count > 1){
                res[0] *= factorial(count.intValue());
            }else {
                res[0] *= factor;
            }

            res[0] = res[0] % 10_0000_0007;
        });

        if(m - max > 1){

        }

        return (int) res[0];
    }

    public static long factorial(int n){
        if(n < 0){
            return 0;
        }

        if(n <= 2){
            facs[n] = n;
            return n;
        }

        if(facs[n] == 0){
            facs[n] = factorial(n - 1) * n;
        }
        return facs[n];
    }
}
