package demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample15 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();

        lock.unlock();

        lock.tryLock();
        try {
            lock.tryLock(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
