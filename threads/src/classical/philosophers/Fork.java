package classical.philosophers;

public class Fork {

    private volatile boolean[] forks = {true, true, true, true, true};

    public synchronized void takeforks(){

        int i = Integer.valueOf(Thread.currentThread().getName());
        // 一直等待
        while( !(forks[i] && forks[(i+1) % 5])){
            try{
               wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 最后拿起叉子
        forks[i] = false;
        forks[(i+1) % 5] = false;
    }

    public synchronized void putforks(){

        int i = Integer.valueOf(Thread.currentThread().getName());
        forks[i] = true;
        forks[(i+1) % 5] = true;
        notifyAll();
    }
}
