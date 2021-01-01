package classical.productorconsumer;

import java.util.Properties;

public class Productor implements Runnable {

    private Bread breads;

    public Productor(){}

    public Productor(Bread bread){
        this.breads = bread;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s producing.. \n", Thread.currentThread().getName());
            breads.produce();
        }
    }
}
