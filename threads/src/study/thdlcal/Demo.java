package study.thdlcal;

public class Demo {


    static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static void main(String[] args) throws InterruptedException {


        Thread t1 =  new Thread(()->{
            System.out.println(threadLocal.get());

            threadLocal.set("t1");

            System.out.println(threadLocal.get());
        },"t1");
        Thread t2 =  new Thread(()->{
            System.out.println(threadLocal.get());

            threadLocal.set("t2");

            System.out.println(threadLocal.get());
        },"t2");


        t1.start();

        // Waits for this thread to die.
        t1.join();

        t2.start();
    }
}
