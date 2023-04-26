package com.java.Collection_;

import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class hashmap {
    @SuppressWarnings("all")
    public static void main(String[] args) {

        HashMap<Integer, String> map = new HashMap<>();

        // Map.Entry entry = new Node() 👉 Node实现了静态Entry接口，向上转型

        // 为节省空间，👇三者保存的是Node[]内部key、value的地址
        Set<Integer> keySet = map.keySet();
        Collection<String> values = map.values();
        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();

        // HashMap线程不安全, HashTable/ConcurrentHashMap线程安全，且KV非空
        // 为何非空？因为在多线程环境下，table.get(key)=null无法判断是对应value值null，还是未找到key返回null
        // 比如线程1正在get(null)或containsKey(null)，线程2同时remove(null)，就会有歧义
        Hashtable<Integer, String> table = new Hashtable<>();

        Properties properties = new Properties();
        properties.put("user", 123456);
        System.out.println(properties);
        properties.setProperty("user", "123");
        System.out.println(properties);
        System.out.println(properties.getProperty("user"));
    }
}
