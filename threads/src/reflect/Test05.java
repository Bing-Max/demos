package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test05 {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Class c1 = Class.forName("reflect.User");

        Field[] fields = c1.getFields();

        for(Field field: fields){
            System.out.println(field);
        }
        System.out.println("=========");

        fields = c1.getDeclaredFields();
        for(Field field:fields){
            System.out.println(field);
        }


        Method[] methods = c1.getMethods();

        for(Method method : methods)
            System.out.println(method);

        Method method = c1.getMethod("getName", null);
        System.out.println(method);

        Method setName = c1.getMethod("setName", String.class);
        System.out.println(setName);

        System.out.println("===========");
        Constructor[] declaredConstructors = c1.getDeclaredConstructors();
        for(Constructor constructor : declaredConstructors){
            System.out.println(constructor);
        }
    }
}
