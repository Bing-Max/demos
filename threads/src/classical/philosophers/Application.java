package classical.philosophers;

public class Application {

    public static void main(String[] args) {
        Thread t1 = new Thread(Philosopher.instnce,"0");
        Thread t2 = new Thread(Philosopher.instnce,"1");
        Thread t3 = new Thread(Philosopher.instnce,"2");
        Thread t4 = new Thread(Philosopher.instnce,"3");
        Thread t5 = new Thread(Philosopher.instnce,"4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
