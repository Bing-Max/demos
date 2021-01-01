package reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Test01 {

    @MyAnnotation01(name = "hello", schools = {"TianjinUniversity"})
    public void test(){

    }

    @MyAnnotation02("hello")
    public void test01(){

    }
}

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation01{
    String name() default "";

    String[] schools();
}

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation02{
    String value();
}
