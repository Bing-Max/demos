package study;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {

    public static void main(String[] args) {

        // 有限资源：停车位，限流可以使用
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "离开车位");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "" + i).start();
        }

    }
}
