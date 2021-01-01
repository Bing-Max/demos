package demo;

public class SynchronizedDifferentMethod7 implements Runnable {
    static SynchronizedDifferentMethod7 instance = new SynchronizedDifferentMethod7();
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
        System.out.printf("method finished! -> %s\n", Thread.currentThread().getName());
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
