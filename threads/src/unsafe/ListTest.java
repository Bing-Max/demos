package unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.ReentrantLock;

public class ListTest {
    public static void main(String[] args) {

//        List<String> list = new ArrayList<>();
//        List<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        AtomicInteger integer = new AtomicInteger();

        ReentrantLock lock = new ReentrantLock();

        AtomicStampedReference<Integer> integerAtomicReference = new AtomicStampedReference<Integer>(1,1);

        integerAtomicReference.compareAndSet(5, 4,1,2);

        integer.getAndIncrement();
//        List<String> list = new CopyOnWriteArrayList<>();
//        for (int i = 0; i < 10; i++) {
//
//            new Thread(()->{
//                list.add(UUID.randomUUID().toString());
//                System.out.println(list);
//            },String.valueOf(i)).start();
//        }

    }
}
