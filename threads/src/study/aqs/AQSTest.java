package study.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class AQSTest {

    public static void main(String[] args) throws InterruptedException {

//        LockSupport.park();
//
//        LockSupport.unpark();


        Thread a = new Thread(() -> {

            System.out.println("a" + "------come in");
            LockSupport.park();
            System.out.println("a" +"第一次被唤醒");
            LockSupport.park();

            System.out.println("a" + "被唤醒");
        }, "a");

        a.start();

        Thread b = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "------come in");
            System.out.println("等待...");

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 发放 Permit
            LockSupport.unpark(a);
            LockSupport.unpark(a);
            System.out.println("b" + "完成工作");

//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            LockSupport.unpark(a);
//            System.out.println("b" + "第二次去唤醒a");
        }, "b");
        b.start();


        ReentrantLock lock = new ReentrantLock();
        lock.lock();

        lock.unlock();

        CountDownLatch countDownLatch = new CountDownLatch(2);

        countDownLatch.await();

        countDownLatch.notify();
    }
}
