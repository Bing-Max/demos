package study.asyc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class FutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            System.out.println("asyc =>");
//        });
//        System.out.println("main");
//        System.out.println(voidCompletableFuture.get());

        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("asyc");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            int i = 1/0;
            return 1024;
        });

        System.out.println("hello");
        int i = integerCompletableFuture.whenCompleteAsync((t, u) -> {
            System.out.println("t=>" + t);//
            System.out.println("u=>" + u); //错误信息java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
        }).exceptionally((e) -> {
            e.getMessage();
            return 233; // 获取返回结果
        }).get();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(i);

    }
}
