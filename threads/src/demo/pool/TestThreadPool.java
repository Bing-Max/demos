package demo.pool;

import java.util.concurrent.*;

public class TestThreadPool {

    public static void main(String[] args) {

        ThreadPoolExecutor pool1 = new ThreadPoolExecutor(2,
                3,
                1,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(5),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.DiscardPolicy()
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        ThreadPoolExecutor pool2 = new ThreadPoolExecutor(2,
                3,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(), //使用无界队列，maxPoolSize就变成了无效参数，新的任务只会加入队列并等待，同理，keepAliveTime也会变成无效参数
                Executors.defaultThreadFactory());


        for (int i = 0; i < 10; i++) {
            pool1.execute(new Task(i));
//            pool2.execute(new Task(i));
        }

        pool1.shutdown();
        System.out.println(Runtime.getRuntime().availableProcessors());
    }


     static class Task implements Runnable{

        private int i;

        Task(int i){
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println("我是线程" + Thread.currentThread().getName() + ":: 我在执行 Task" + i);
        }
    }



}
