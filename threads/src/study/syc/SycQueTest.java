package study.syc;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SycQueTest {
    public static void main(String[] args) {
        test1();
    }

    public static void test1(){

        SynchronousQueue<String> strings = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName() + "put1");
                strings.put(1+"");
                System.out.println(Thread.currentThread().getName() + "put2");
                strings.put(2+"");
                System.out.println(Thread.currentThread().getName() + "put3");
                strings.put(3+"");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName() + "get:" + strings.take());
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + "get:" + strings.take());
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + "get:" + strings.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();
    }
}
