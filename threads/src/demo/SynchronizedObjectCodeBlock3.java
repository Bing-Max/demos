package demo;

public class SynchronizedObjectCodeBlock3 implements Runnable {
    static SynchronizedObjectCodeBlock3 instance = new SynchronizedObjectCodeBlock3();
    @Override
    public void run() {
        System.out.printf("%s start.\n", Thread.currentThread().getName());
        method();
    }



    public synchronized void method() {
        System.out.println("method!");
        System.out.println(Thread.currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);

        t1.start();
        t2.start();


    }

}
