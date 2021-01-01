package study.fkjn;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test1();//3193
//        test2(); //times : 4437
//        test3(); //150
    }

    public static void test1(){
        Long sum = 0L;
        long start = System.currentTimeMillis();
        for (long i = 0; i <= 10_0000_0000; i++) {
            sum += i;
        }

        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum + " times : " + (end - start));
    }

    public static void test2() throws ExecutionException, InterruptedException {
        Long sum = 0L;
        long start = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinDemo forkJoinDemo = new ForkJoinDemo(0L, 10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(forkJoinDemo);
        sum = submit.get();

        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum + " times : " + (end - start));
    }

    public static void test3() {
        Long sum = 0L;
        long start = System.currentTimeMillis();

        sum = LongStream.rangeClosed(0L,10_0000_0000L).parallel().reduce(0,Long::sum);
        long end = System.currentTimeMillis();
        System.out.println("sum = " + sum + " times : " + (end - start));
    }


}
