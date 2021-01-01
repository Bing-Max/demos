package study.bq;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        test4();
    }

    public static void test1(){
        ArrayBlockingQueue objects = new ArrayBlockingQueue<>(3);
        System.out.println(objects.add("a"));
        System.out.println(objects.add("b"));
        System.out.println(objects.add("c"));
//        System.out.println(objects.add("d"));
        objects.element();

        System.out.println(objects.remove().toString());
        System.out.println(objects.remove().toString());
        System.out.println(objects.remove().toString());
        System.out.println(objects.remove().toString());

    }

    public static void test2(){
        ArrayBlockingQueue objects = new ArrayBlockingQueue<>(3);
        System.out.println(objects.offer("a"));
        System.out.println(objects.offer("c"));
        System.out.println(objects.offer("d"));
//        System.out.println(objects.offer("b"));

        System.out.println(objects.poll());
        System.out.println(objects.poll());
        System.out.println(objects.poll());
        System.out.println(objects.poll());
    }

    public static void test3() throws InterruptedException {
        ArrayBlockingQueue objects = new ArrayBlockingQueue<>(3);
        objects.put("a");
        objects.put("b");
        objects.put("c");
//        objects.put("d");

        System.out.println(objects.take());
        System.out.println(objects.take());
        System.out.println(objects.take());
        System.out.println(objects.take());
    }

    public static void test4() throws InterruptedException {
        ArrayBlockingQueue objects = new ArrayBlockingQueue<>(3);
        objects.offer("a");
        objects.offer("b");
        objects.offer("c");
        objects.offer("d", 2, TimeUnit.SECONDS);

        System.out.println(objects.poll());
        System.out.println(objects.poll());
        System.out.println(objects.poll());
        System.out.println(objects.poll(2,TimeUnit.SECONDS));

    }

}
