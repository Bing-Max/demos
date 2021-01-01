package demo;


public class SynchronizedClassClass5 implements Runnable {
    static SynchronizedClassClass5 instance1 = new SynchronizedClassClass5();
    static SynchronizedClassClass5 instance2 = new SynchronizedClassClass5();
    @Override
    public void run() {
        method();
    }

    public void method(){
        synchronized (SynchronizedClassClass5.class){
            System.out.printf("method! -- %s\n",Thread.currentThread().getName());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("finished! -- %s\n",Thread.currentThread().getName());
        }

    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);

        t1.start();
        t2.start();
    }
}
