package study;

import javax.management.StandardEmitterMBean;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(7,()->{
            System.out.println("召唤神龙");
        });

        for (int i = 0; i < 7; i++) {

            final int temp = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "收集了第" + temp + "龙珠");

                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName() + "结束了");
            }).start();
        }
    }
}
