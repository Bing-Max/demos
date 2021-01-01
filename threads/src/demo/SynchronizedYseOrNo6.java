package demo;

public class SynchronizedYseOrNo6 implements Runnable {
    static SynchronizedYseOrNo6 instance = new SynchronizedYseOrNo6();

    @Override
    public void run() {
        if(Thread.currentThread().getName().equals("Thread-0")){
            method1();
        }else{
            method2();
        }

    }

    public synchronized void method1(){
        System.out.printf("method1 ! -> %s\n",Thread.currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void method2(){
        System.out.printf("method2 ! -> %s\n",Thread.currentThread().getName());

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
