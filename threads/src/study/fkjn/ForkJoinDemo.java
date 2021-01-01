package study.fkjn;

import classical.philosophers.Fork;

import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo extends RecursiveTask<Long> {

    /**
     * 1. ForkJoin
     * 2. Stream 并行流
     * @param args
     */
    public static void main(String[] args) {
        int sum = 0;

        for (int i = 0; i < 10_0000_0000; i++) {

        }
    }

    private Long start;
    private Long end;

    private Long temp = 10000L;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        Long sum = 0l;
        if((end - start) <= temp){
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }else {
//            int count = (int) ((start - end) / temp + 1);
//            ForkJoinDemo[] tasks = new ForkJoinDemo[count];
//            for(int i = 0; i < count; i++){
//                tasks[i] = new ForkJoinDemo(start + i * temp, start + (i + 1) * temp);
//                tasks[i].fork();
//            }
//
//            for(ForkJoinDemo task : tasks)
//                sum += (Long) task.join();

            Long mid = (start + end) /2 ;
            ForkJoinDemo task1 = new ForkJoinDemo(start, mid);
            task1.fork();
            ForkJoinDemo task2 = new ForkJoinDemo(mid + 1, end);
            task2.fork();

            return task1.join() + task2.join();

        }
    }
}
