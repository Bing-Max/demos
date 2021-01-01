package study;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        // 倒计时，总数为6
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + " go out");
            },"" + i).start();
        }
        countDownLatch.await(); // 等待计数器归零
        System.out.println("close the door");

    }
}
