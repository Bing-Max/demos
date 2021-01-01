package demo;

public class SynchronizedRecursion10 {
    int a = 0;

    public static void main(String[] args) {
        SynchronizedRecursion10 obj = new SynchronizedRecursion10();

        obj.method1();
    }

    private synchronized void method() {
        System.out.printf("我是方法1， a = %d\n", a);
        if(a == 0){
            a++;
            method();
        }
    }


    private synchronized  void method1(){
        System.out.println("我是方法1 ");
        method2();
    }

    private synchronized void method2() {
        System.out.printf("我是方法2， a = %d\n", a);
        if(a == 0){
            a++;
            method();
        }
    }


}
