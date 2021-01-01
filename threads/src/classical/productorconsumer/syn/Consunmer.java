package classical.productorconsumer.syn;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Consunmer {

    public static void main(String[] args) {
        C c = new C();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                c.printA();
            }
        }, "A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                c.printB();
            }
        }, "B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                c.printC();
            }
        }, "C").start();

    }
}

class C{
    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void printA(){

        lock.lock();
        try {
            while (num != 1){
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "->AAAAA");
            num = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB(){

        lock.lock();
        try {
            while (num != 2){
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "->BBBBB");
            num = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printC(){

        lock.lock();
        try {
            while (num != 3){
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "->CCCCC");
            num = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}