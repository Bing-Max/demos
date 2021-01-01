package study.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();//单个线程
        ExecutorService executorService1 = Executors.newFixedThreadPool(5);//创建一个固定的线程池大小
        ExecutorService executorService2 = Executors.newCachedThreadPool();// 可伸缩，遇强则强，遇弱则弱

        try {
            for (int i = 0; i < 10; i++) {
//                executorService.execute(()->{
//                    System.out.println(Thread.currentThread().getName());
//                });

//                executorService1.execute(()-> {
//                    System.out.println(Thread.currentThread().getName());
//                });
                executorService2.execute(()->{
                    System.out.println(Thread.currentThread().getName());
                });
            }
        } finally {
            executorService.shutdown();
            executorService1.shutdown();
            executorService2.shutdown();
        }


    }
}
