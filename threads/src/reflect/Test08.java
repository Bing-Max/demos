package reflect;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Types;
import java.util.List;
import java.util.Map;

public class Test08 {

    public void test01(Map<String, User> map, List<User> list){
        System.out.println("test01");
    }

    public Map<String, User> test02(){
        System.out.println("test02");
        return null;
    }

    public static void main(String[] args) throws NoSuchMethodException {

        Method test01 = Test08.class.getMethod("test01", Map.class, List.class);

        Type[] genericParameterTypes = test01.getGenericParameterTypes();
        for(Type type : genericParameterTypes){
            System.out.println(type);
            if(type instanceof ParameterizedType){

                // 获取真实参数类型
                Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
                for(Type type1 : actualTypeArguments)
                    System.out.println(type1);
            }
        }
    }
}
