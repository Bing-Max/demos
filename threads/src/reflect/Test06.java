package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test06 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class c1 = Class.forName("reflect.User");

        User user = (User) c1.newInstance();
        // 调用无参构造器

        Constructor c = c1.getConstructor(String.class, int.class, int.class);
        User user2 = (User) c.newInstance("bing", 1, 21);
        System.out.println(user2);

        User user3 = (User) c1.newInstance();
        Method setName = c1.getMethod("setName", String.class);
        setName.invoke(user3, "bing");
        System.out.println(user3);

        User user4 = (User) c1.newInstance();
        Field name = c1.getDeclaredField("name");

        name.setAccessible(true);
        name.set(user4, "bing");
        System.out.println(user4.getName());
    }
}
