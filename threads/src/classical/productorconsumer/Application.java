package classical.productorconsumer;

public class Application {

    public static void main(String[] args) {
        Bread bread = new Bread();

        new Thread(new Productor(bread),"producer-1").start();
        new Thread(new Productor(bread),"producer-2").start();

        new Thread(new Consumer(bread),"consumer - 1").start();
        new Thread(new Consumer(bread), "consumer - 2").start();
        new Thread(new Consumer(bread), "consumer - 3").start();
        new Thread(new Consumer(bread), "consumer - 4").start();
        new Thread(new Consumer(bread), "consumer - 5").start();

    }
}
