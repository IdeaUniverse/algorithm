package leetcode;

import common.Test;
import common.Utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * LRU 缓存机制
 * https://leetcode-cn.com/problems/lru-cache/
 */
public class Q146 implements Test {

    @Override
    public boolean test() {
        int capacity = 5;
        Cache<Integer, Integer> cache = new Cache<>(capacity);
        LinkedHashMap<Integer, Integer> linkedHashMap = new LruLinkedHashMap<>(capacity);
        for (int i = 1; i <= capacity; i++) {
            cache.set(i, i);
            linkedHashMap.put(i,i);
        }
        Utils.print("原始Cache", cache);
        Utils.print("原始LinkedHashMap", linkedHashMap);

        int start = new Random().nextInt(capacity);
        int end = new Random().nextInt(capacity * 2);
//        start = 1; end  = 18;
        Utils.print("从", start ,"到", end, "设置新值");
        for (int i = start; i < end; i++) {   // 随机增新的值
            cache.set(i, i);
            linkedHashMap.put(i, i);
        }
        Utils.print("最新Cache", cache);
        Utils.print("最新LinkedHashMap", linkedHashMap);

        return cache.toString().equals(linkedHashMap.toString());
    }

    public static void main(String[] args) {
        Utils.batchTest(new Q146(), 100);
    }
}

class Cache<K, V> {

    private Map<K, Node<K, V>> map = new HashMap<>();
    private LinkedList<K, V> list = new LinkedList<>();

    private int capacity;

    public Cache(int capacity){
        this.capacity = capacity;
    }

    public V get(K key){
        Node<K, V> node = map.get(key);
        if(node != null){
            return node.value;
        }
        return null;
    }

    public void set(K key, V value){
        if(map.containsKey(key)){
            Node<K, V> node = map.get(key);
            node.value = value;
        } else {
            if(map.size() >= capacity){
                Node<K, V> lastNode = list.getLast();
                map.remove(lastNode.key);
                list.removeNode(lastNode);
            }
            Node<K, V> node = new Node<>(key,value);
            map.put(key, node);
            list.addHead(node);
        }
    }

    @Override
    public String toString(){
        return list.toString();
    }
}

class Node<K, V> {
    public Node<K,V> prev;
    public Node<K,V> next;
    public K key;
    public V value;

    public Node(K key, V value){
        this.key = key;
        this.value = value;
    }

    public Node(){}

}

class LinkedList<K, V> {
    private final Node<K, V> head = new Node<>();
    private final Node<K, V> tail = new Node<>();

    public LinkedList(){
        head.next = tail;
        tail.prev = head;
    }

    public void addHead(Node<K, V> node){
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public void removeNode(Node<K, V> node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = node.next = null;
    }

    public Node<K, V> getLast() {
        return tail.prev;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder("{");
        Node<K, V> node = tail;
        while (node.prev != null){
            node = node.prev;
            if(node.prev != null){
                s.append(node.key).append("=").append(node.value);
                if(node.prev.prev != null){
                    s.append(", ");
                }
            }
        }
        s.append("}");
        return s.toString();
    }
}

class LruLinkedHashMap<K,V> extends LinkedHashMap<K,V>{

    private final int capacity;

    public LruLinkedHashMap(int capacity){
        super(capacity, 0.75f, false);
        this.capacity = capacity;
    }

    @Override
    public boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return size() > capacity;
    }
}