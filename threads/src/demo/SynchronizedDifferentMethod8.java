package demo;

public class SynchronizedDifferentMethod8 implements Runnable{
    static SynchronizedDifferentMethod8 instance1 = new SynchronizedDifferentMethod8();
    static SynchronizedDifferentMethod8 instance2 = new SynchronizedDifferentMethod8();
    @Override
    public void run() {
        if(Thread.currentThread().getName().equals("Thread-0")){
            method1();
        }else{
            method2();
        }

    }

    public static synchronized void method1(){
        System.out.printf("static method1 ! -> %s\n",Thread.currentThread().getName());

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
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);

        t1.start();
        t2.start();


    }
}
