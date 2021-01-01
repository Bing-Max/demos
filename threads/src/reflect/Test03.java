package reflect;

public class Test03 {

    public static void main(String[] args) throws ClassNotFoundException {
        // 方式一： Class.forName()
        Class c1 = Class.forName("reflect.Student");
        System.out.println(c1.hashCode());

        // 方式二： 通过对象获得
        Person p = new Student();
        Class c2 = p.getClass();
        System.out.println(c2.hashCode());

        // 方式三： 通过.class属性获得
        Class c3 = Student.class;
        System.out.println(c3.hashCode());

        // 方式四： 内置基本类型 .TYPE
        Class c4 = Integer.TYPE;
        System.out.println(c4);

        // 获得父类
        Class c5 = c1.getSuperclass();
        System.out.println(c5);
    }
}

class Person{
    String name;

    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }
}

class Student extends Person {
    public Student(){
        this.name = "学生";
    }
}

class Teacher extends Person {
    public Teacher(){
        this.name = "老师";
    }
}

