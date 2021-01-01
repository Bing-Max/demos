package demo;

public class DisappearedRequest implements Runnable{
    static DisappearedRequest instance = new DisappearedRequest();
    static int count = 0;
    @Override
    public void run() {
//        System.out.printf("%s start.\n", Thread.currentThread().getName());
        synchronized (DisappearedRequest.class){

            for(int i = 0 ; i < 100000; i++){
                count++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
//        while (t1.isAlive() || t2.isAlive()){
//
//        }
        t1.join();
        t2.join();

        System.out.println(count);
    }


}
