package reflect;

import java.lang.annotation.*;

public class Test09 {

    public static void main(String[] args) throws NoSuchFieldException {

        Class<User2> user2Class = User2.class;

        Annotation[] annotations = user2Class.getAnnotations();
        for(Annotation annotation : annotations)
            System.out.println(annotation);

        Table annotation = user2Class.getAnnotation(Table.class);
        System.out.println(annotation.value());

        java.lang.reflect.Field id = user2Class.getDeclaredField("name");
        Field field = id.getAnnotation(Field.class);
        System.out.println(field);
        System.out.println(field.type());
        System.out.println(field.length());

    }

}

@Table("db_student")
class User2{

    @Field(colName = "id", type = "int", length = 10)
    private int id;

    @Field(colName = "name", type = "String", length = 10)
    private String name;

    @Field(colName = "age", type = "int", length = 3)
    private int age;

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Table{
    String value();
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Field{
    String colName();
    String type();
    int length();

}


