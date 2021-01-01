package study.alg;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheDemo extends LinkedHashMap {

    public int capacity;

    /**
     * Constructs an empty <tt>LinkedHashMap</tt> instance with the
     * specified initial capacity, load factor and ordering mode.
     *
     * param  initialCapacity the initial capacity
     * param  loadFactor      the load factor
     * param  accessOrder     the ordering mode - <tt>true</tt> for
     *         access-order, <tt>false</tt> for insertion-order
     * @throws IllegalArgumentException if the initial capacity is negative
     *         or the load factor is nonpositive
     */
    public LRUCacheDemo(int capacity){
        super(capacity, 0.75f, false);
        this.capacity = capacity;
    }

    /**
     *
     * @param eldest
     * @return   <tt>true</tt> if the eldest entry should be removed
     *           from the map; <tt>false</tt> if it should be retained.
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUCacheDemo lruCacheDemo = new LRUCacheDemo(3);

        lruCacheDemo.put(1, "a");
        lruCacheDemo.put(2, "b");
        lruCacheDemo.put(3, "c");

        System.out.println(lruCacheDemo.keySet());
        lruCacheDemo.put(4, "d");
        System.out.println(lruCacheDemo.keySet());

        lruCacheDemo.put(3, "c");

        System.out.println(lruCacheDemo.keySet());
        lruCacheDemo.put(3, "c");

        System.out.println(lruCacheDemo.keySet());
        lruCacheDemo.put(3, "c");

        System.out.println(lruCacheDemo.keySet());
        lruCacheDemo.put(5, "x");
        System.out.println(lruCacheDemo.keySet());
        lruCacheDemo.put(6,"y");
        System.out.println(lruCacheDemo.keySet());


    }
}
