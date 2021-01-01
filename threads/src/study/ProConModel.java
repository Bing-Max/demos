package study;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ProConModel {

    private static AtomicInteger id = new AtomicInteger(0);
    private static volatile int count = 0;
    private static Queue<Bread> que = new ConcurrentLinkedDeque<>();

    public static void main(String[] args) throws InterruptedException {
        Thread proucer = new Thread(new Producer());
        proucer.start();

        Thread consumer = new Thread(new Consumer());

        consumer.start();

        TimeUnit.SECONDS.sleep(30);

        proucer.interrupt();
        consumer.interrupt();

        System.out.println("exit");
    }

    public static class Bread{
        int curId;

        public Bread(int id){
            this.curId = id;
        }
    }

    public static class Consumer implements Runnable{
        Bread bread;
        @Override
        public void run() {
            consume();
        }

        public void consume(){

            while (true){
                while (count > 0){

                    bread = que.poll();
                    count--;
                    System.out.println("consume ::" + bread.curId);
                }
            }
        }


    }

    public static class Producer implements Runnable{
        int cur = 0;
        @Override
        public void run() {
            produce();
        }

        public void produce(){

            while (true){
                while (count < 10){
                    cur = id.incrementAndGet();
                    que.add(new Bread(cur));
                    System.out.println("produce new -----" + cur);
                    count++;
                }
            }
        }

    }
}
