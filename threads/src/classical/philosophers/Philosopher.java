package classical.philosophers;

public class Philosopher implements Runnable {
    static Philosopher instnce = new Philosopher();

    private static Fork fork = new Fork();
    @Override
    public void run() {
        while (true){
            thinking();
            fork.takeforks();
            eating();
            fork.putforks();
        }
    }

    public void thinking(){
        System.out.printf("%s start thinking...\n", Thread.currentThread().getName());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("%s finished thinking...\n",Thread.currentThread().getName());

    }

    public void eating(){
        System.out.printf("%s start eating...\n", Thread.currentThread().getName());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("%s finished eating...\n",Thread.currentThread().getName());
    }
}
