package demo.pool;

import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class DealWithDirLamThreadPool {


    private static AtomicInteger count = new AtomicInteger();
    private static ThreadPoolExecutor executor = null;
    private static LinkedBlockingDeque<File> deque = new LinkedBlockingDeque<>();
    private static ConcurrentHashMap<String, String> res = new ConcurrentHashMap<>();

    private static class MyTask implements Runnable{

        @Override
        public void run() {
            if (!deque.isEmpty()){
                File file = deque.poll();

                Arrays.stream(file.listFiles()).peek(file1 -> {
                    if(file1.isDirectory()){
                        count.getAndIncrement();
                        deque.add(file1);
                        executor.execute(new MyTask());
                    }
                }).filter(file1 -> file1.getName().endsWith(".png"))
                        .map(file1 -> file1.getAbsolutePath())
                        .forEach(file1 -> res.put(file1,Thread.currentThread().getName()));
            }

            int x = count.decrementAndGet();
            if(x == 0){
                executor.shutdown();
            }
        }
    }

    public static void printWithLevel(int l){
        Map<String, Long> collect = res.keySet().stream().collect(Collectors.groupingBy(s -> s.split("\\\\")[l], Collectors.counting()));
        System.out.println(res.size());
        System.out.println(collect);
    }

    public static void main(String[] args) {
        executor = new ThreadPoolExecutor(5,
                10,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        count.getAndIncrement();
        deque.add(new File("E:\\Git-Repo\\myNote"));
        executor.execute(new MyTask());

        while (0 != count.get());

        printWithLevel(3);
    }
}
