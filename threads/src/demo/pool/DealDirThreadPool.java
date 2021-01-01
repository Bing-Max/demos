package demo.pool;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class DealDirThreadPool {

    private static AtomicInteger count = new AtomicInteger(0); // count the task of dir， add task to queue ->count++; run() finished count--;

    private static ConcurrentHashMap<String, String> res = new ConcurrentHashMap<>(); // store all the absPath of .pngs and records which one dealing with it;

    private static ThreadPoolExecutor executor;

    static class DirDealingTask implements  Runnable{

        private File dir;

        public DirDealingTask(File file){
            if(file.isDirectory()){
                this.dir = file;
            }else {
                System.out.println(Thread.currentThread().getName() + " is Dealing...Not a dir!!!");
            }
        }


        @Override
        public void run() {
            List<File> collect = Arrays.stream(dir.listFiles()).collect(Collectors.toList());

            collect.stream().peek(file -> {
                if(file.isDirectory()){
                    count.getAndIncrement();
                    executor.execute(new DirDealingTask(file));
                }
            }).filter(file -> file.getName().endsWith(".png") || file.getName().endsWith(".jpg"))
                    .forEach(file -> {
                        res.put(file.getAbsolutePath(), Thread.currentThread().getName());
                    });

            int x = count.decrementAndGet();
            if(x == 0){
                executor.shutdown();
            }

        }
    }

    public static void printWithLevel(int l){
        Map<String, Long> collect = res.keySet().stream().collect(Collectors.groupingBy(s -> s.split("\\\\")[l], Collectors.counting()));

        System.out.println(collect);
    }


    public static void main(String[] args) throws InterruptedException {
        executor =  new ThreadPoolExecutor(5,10,60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

        count.getAndIncrement();
        executor.execute(new DirDealingTask(new File("E:\\Git-Repo\\myNote")));
//        executor.execute(new DirDealingTask(new File("E:\\素材")));
        while (0 != count.get());
//        while (0!= executor.getActiveCount());
        System.out.println(res.size());

//        System.out.println(res);
        printWithLevel(3);
    }
}
