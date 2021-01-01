package demo;

public class SynchronizedClassStatic4 implements Runnable {
    static SynchronizedClassStatic4 instance1 = new SynchronizedClassStatic4();
    static SynchronizedClassStatic4 instance2 = new SynchronizedClassStatic4();
    @Override
    public void run() {
        method();
    }

    public static synchronized void method(){
        System.out.printf("method! -- %s\n",Thread.currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("finished! -- %s\n",Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);

        t1.start();
        t2.start();
    }


}
