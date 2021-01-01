package study;

import java.util.concurrent.TimeUnit;

public class ParentWait {

    private static volatile boolean stop;

    private static boolean isStop;

    public static synchronized boolean getStop(){
        return isStop;
    }

    public static synchronized void setStop(){
        isStop = true;
    }

    public static void main(String[] args) {
        testInterrupt();

        testVolatile();

        testSync();
    }

    public static void testInterrupt(){

        Thread child = new Thread(new InterruptChild());
        child.start();

        while (!child.isInterrupted()){

        }

        System.out.println(Thread.currentThread().getName() + "-------parent - Inter close");
    }

    public static void testVolatile(){
        Thread child = new Thread(new VolatileChild());

        child.start();

        while (!stop){

        }
        System.out.println(Thread.currentThread().getName() + "-------parent - Vol close");

    }

    public static void testSync(){
        Thread child = new Thread(new SyncChild());

        child.start();

        while (!getStop()){

        }

        System.out.println(Thread.currentThread().getName() + "---------parent - Sync close");
    }

    public static class VolatileChild implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "------start");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            stop = true;
        }
    }

    public static class SyncChild implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "------start");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            setStop();
        }
    }

    public static class InterruptChild implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "------start");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Thread.currentThread().interrupt();
        }
    }
}
