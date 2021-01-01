package demo;

public class SynchronizedObjectCodeBlock2 implements Runnable {
    static SynchronizedObjectCodeBlock2 instance = new SynchronizedObjectCodeBlock2();

    Object lock1 = new Object();
    Object lock2 = new Object();
    @Override
    public void run() {
        synchronized (lock1){
            System.out.printf("%s start.\n", Thread.currentThread().getName());
            System.out.println("Object Lock! - lock1");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.printf("%s finished.\n", Thread.currentThread().getName());
        }

        synchronized (lock2){
//            System.out.printf("%s start.\n", Thread.currentThread().getName());
            System.out.println("Object Lock!- lock2");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.printf("%s finished.\n", Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);

        t1.start();
        t2.start();


    }
}
