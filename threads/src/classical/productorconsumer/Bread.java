package classical.productorconsumer;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Bread {

    // 最多可以生产十个面包
    static final int MAX_CONTAINER = 10;

    private volatile Integer count = 0;

    public synchronized void produce(){
        // 生产面包
            while (count+1 > MAX_CONTAINER){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("剩余面包：%d\n", ++count);

//            if(count == MAX_CONTAINER){
//                notifyAll();
//            }
//            count.notifyAll();
    }

    public synchronized void consume(){
        // 消耗面包

            while (count == 0 ){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("剩余面包：%d\n", --count);
//            count.notifyAll();
        if(count == 0){
            notifyAll();
        }

    }

    public int getCount(){
        return count;
    }
}
