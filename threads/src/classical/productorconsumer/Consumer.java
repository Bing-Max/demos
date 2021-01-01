package classical.productorconsumer;

public class Consumer implements Runnable {

    Bread bread;

    public Consumer(){}

    public Consumer(Bread bread){
        this.bread = bread;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s consuming.. \n", Thread.currentThread().getName());
            bread.consume();
        }
    }
}
