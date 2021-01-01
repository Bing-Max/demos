package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test07 {

    public static void test01(){
        User user = new User();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            user.getName();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("普通方法：" + (endTime - startTime) + "ms");


    }

    public static void test02() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        User user = new User();
        Class aClass = user.getClass();
        Method getName = aClass.getMethod("getName", null);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(user, null);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("反射"+ (endTime - startTime) + "ms");
    }
    public static void test03() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        User user = new User();
        Class aClass = user.getClass();
        Method getName = aClass.getMethod("getName", null);
        getName.setAccessible(true);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            getName.invoke(user, null);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("反射关闭保护检测"+ (endTime - startTime) + "ms");
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        test01();
        test02();
        test03();
    }

}
