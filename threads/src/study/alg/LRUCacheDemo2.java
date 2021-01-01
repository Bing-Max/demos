package study.alg;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LRUCacheDemo2<K,V> {

    public int capacity;
    public Map<K,Node> map;
    public DoubleLinkedList linkedList;


    static class Node<K,V>{
        K key;
        V value;

        Node<K,V> prev;
        Node<K,V> next;

        public Node(){
            prev = null;
            next = null;
        }

        public Node(K key, V value){
            this.key = key;
            this.value = value;

            this.prev = this.next = null;
        }

    }

    static class DoubleLinkedList<K,V>{

        Node<K,V> head;
        Node<K,V> tail;

        int length;

        public DoubleLinkedList(){
            this.head = new Node<>();
            this.tail = new Node<>();

            head.next = tail;
            tail.prev = head;

            length = 0;
        }

        public void addHeadNode(Node node){
            node.prev = head;
            node.next = head.next;

            node.next.prev = node;
            node.prev.next = node;

            length++;
        }

        public Node getLast(){
            if(length > 0)
                return tail.prev;

            return null;
        }

        public Node removeTailNode(){
            if(length > 0){
                Node<K,V> last = tail.prev;
                removeNode(last);

                return last;
            }

            return null;
        }

        private void removeNode(Node node){
            node.prev.next = node.next;
            node.next.prev = node.prev;

            node.prev = null;
            node.next = null; // help GC

            length--;
        }


    }

    public LRUCacheDemo2(){
        this.capacity = 2; // default
        map = new HashMap<>();
        linkedList = new DoubleLinkedList();
    }

    public LRUCacheDemo2(int capacity){
        this.capacity = capacity;
        map = new HashMap<>();
        linkedList = new DoubleLinkedList();
    }

    public V get(K key){
        if(map.containsKey(key))
            return (V) map.get(key).value;
        return null;
    }

    public void printKeys(){
        Node node = linkedList.head.next;
        StringBuilder sub = new StringBuilder("[");

        for(;;){
            if(node == linkedList.tail){
                break;
            }

            sub.append(node.key);
            sub.append(", ");
            node = node.next;
        }
        sub.delete(sub.length() - 2,sub.length());
        sub.append("]");

        System.out.println(sub.toString());
    }

    public void put(K key, V value){
        if(map.containsKey(key)){
            Node node = map.get(key);
            // update
            node.value = value;
            if(node != linkedList.head.next){
                // 更新链表
                linkedList.removeNode(node);
                linkedList.addHeadNode(node);
            }
        }else {
            if(map.size() == capacity){
                // 淘汰老结点
                final Node last = linkedList.getLast();
                map.remove(last.key);
                linkedList.removeTailNode();

                Node<K, V> node = new Node<>(key, value);
                map.put(key, node);

                linkedList.addHeadNode(node);

            }else {
                Node<K, V> node = new Node<>(key, value);
                map.put(key, node);

                linkedList.addHeadNode(node);
            }
        }
    }

    public static void main(String[] args) {
        LRUCacheDemo2<Integer, String> demo = new LRUCacheDemo2<>();

        demo.put(1,"a");
        demo.put(2,"b");
        demo.put(3,"c");

        demo.printKeys();

        demo.put(4,"d");
        demo.printKeys();

        demo.put(3,"c");
        demo.printKeys();

        demo.put(3,"c");
        demo.printKeys();

        demo.put(3,"c");
        demo.printKeys();

        demo.put(5,"x");
        demo.printKeys();
        demo.put(6,"y");
        demo.printKeys();
    }

}
