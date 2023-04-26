package com.Ex.DAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAO<T> {
    Map<String, T> map = new HashMap<>();

    public DAO() {
    }

    // 保存T类型到Map的成员变量
    public void save(String id, T t) {
        map.put(id, t);
    }

    public T get(String id) {
        T t = map.getOrDefault(id, null);
        if (t == null) {
            System.out.println("没找到 " + id);
        }
        return t;
    }

    public void update(String id, T t) {
        if (map.containsKey(id)) {
            map.put(id, t);
        } else {
            System.out.println("没找到 " + id);
        }
    }

    public List<T> list() {
        Collection<T> coll = map.values();
        return new ArrayList<T>(coll);
    }

    public void delete(String id) {
        if (map.containsKey(id)) {
            map.remove(id);
        } else {
            System.out.println("没找到 " + id);
        }
    }
}
