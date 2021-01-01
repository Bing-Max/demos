package unsafe;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class MapTest {

    public static void main(String[] args) throws InterruptedException {
        Map<String, String> map;

        map = new HashMap<>();

        for (int i = 0; i < 3000; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
            }, "" + i).start();
        }

        TimeUnit.SECONDS.sleep(5);
        System.out.println(map);
        System.out.println(map.size());
    }
}
