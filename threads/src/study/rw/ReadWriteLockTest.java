package study.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

    public static void main(String[] args) {
//        MyCache myCache = new MyCache();
        MyCacheLock myCache = new MyCacheLock();

        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.put(temp + "", temp);
            }).start();
        }

        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.get(temp + "");
            }).start();
        }
    }
}

class MyCache{
    private volatile Map<String ,Object> map = new HashMap<>();

    // 写入
    public void put(String key, Object value){
        System.out.println("写入" + key);
        map.put(key,value);
        System.out.println("写入" + key + "完毕");
    }

    public Object get(String key){
        System.out.println("读取"+ key);
        return map.get(key);
    }
}

class MyCacheLock{
    private volatile Map<String ,Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    // 写入
    public void put(String key, Object value){

        readWriteLock.writeLock().lock();

        try {
            System.out.println("写入" + key);
            map.put(key,value);
            System.out.println("写入" + key + "完毕");
        } finally {
            readWriteLock.writeLock().unlock();
        }


    }

    public Object get(String key){

        readWriteLock.readLock().lock();
        try {

            System.out.println("读取"+ key);
            return map.get(key);
        }finally {
            readWriteLock.readLock().unlock();
        }
    }
}