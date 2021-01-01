package demo;

/**
 *
 */
public class SynchronizedException9 implements Runnable {
    static SynchronizedException9 instance = new SynchronizedException9();
    @Override
    public void run() {
        method1();
    }

    public synchronized void method1(){
        System.out.printf("method1 ! -> %s\n",Thread.currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
//        System.out.printf("method finished! -> %s\n", Thread.currentThread().getName());
    }

    public synchronized void method2(){
        System.out.printf("method2 ! -> %s\n",Thread.currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("method finished! -> %s\n", Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);

        t1.start();
        t2.start();


    }
}
