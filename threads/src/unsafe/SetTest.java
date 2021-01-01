package unsafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetTest {
    public static void main(String[] args) {

//        HashSet<String> set = new HashSet<>();
//        Set<String> set = new CopyOnWriteArraySet<>();
        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        for (int i = 0; i < 10; i++) {

            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set.toString());
            }, "" + i).start();
        }
    }
}
