package demo;

public class SynchronizedSuperClass12 extends  SynchronizedSuperClass {
    @Override
    public synchronized void doSomething() {
        System.out.println("我是子类doSomething");
        super.doSomething();
    }

    public SynchronizedSuperClass12(){
        doSomething();
    }

    public static void main(String[] args) {
        SynchronizedSuperClass12 obj = new SynchronizedSuperClass12();
    }

}
