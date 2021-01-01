package classical.productorconsumer.syn;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Data {
    private int num = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();


    public void increment() throws InterruptedException {
        lock.lock();
        while(num != 0){
            condition.await(); //等待
        }
        num++;
        System.out.println(Thread.currentThread().getName() + "-》" + num);
        //通知其他线程
        condition.signalAll();

        lock.unlock();
    }

    public void decrement() throws InterruptedException {

        lock.lock();
        while(num == 0){
            condition.await();
        }
        num--;
        System.out.println(Thread.currentThread().getName() + "-》" + num);
        condition.signalAll();

        lock.unlock();
    }
}
