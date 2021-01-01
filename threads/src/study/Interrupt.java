package study;

import java.util.concurrent.TimeUnit;

public class Interrupt {
    public static void main(String[] args) throws InterruptedException {
        Runner r1 = new Runner();

        Runner r2 = new Runner();

        Thread countThread = new Thread(r1);
        countThread.start();

        TimeUnit.SECONDS.sleep(1);
        countThread.interrupt();

        Thread thread = new Thread(r2);
        thread.start();

        TimeUnit.SECONDS.sleep(1);
        r2.cancel();


    }

    public static class Runner implements Runnable{
        private long a;
        private boolean stop = false;
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "-------start");
            while (!stop && !Thread.currentThread().isInterrupted()){
                a++;
            }
            System.out.println(a);
        }

        public void cancel(){
            this.stop = true;
        }

    }
}
