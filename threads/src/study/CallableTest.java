package study;

import java.util.concurrent.*;

public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask myTask = new MyTask();
        FutureTask<String> stringFutureTask = new FutureTask<>(myTask);
        CountDownLatch c= new CountDownLatch(2);
        Semaphore semaphore = new Semaphore(2);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        new Thread(stringFutureTask).start();
        new Thread(stringFutureTask).start();// 缓存，提高效率
        String str = stringFutureTask.get();
        System.out.println(str);    //可能会阻塞
    }
}

class MyTask implements Callable<String>{

    @Override
    public String  call() throws Exception {
        System.out.println("hello");
        return "null";
    }
}
