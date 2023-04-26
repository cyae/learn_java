package com.Data_Structure.UnionFind;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

class Element<V> {
    V val;

    public Element(V val) {
        this.val = val;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

public class UnionFind<V> {
    HashMap<V, Element<V>> elementMap;
    HashMap<Element<V>, Element<V>> fatherMap;
    HashMap<Element<V>, Integer> sizeMap;

    public UnionFind(List<V> list) {
        elementMap = new HashMap<>();
        fatherMap = new HashMap<>();
        sizeMap = new HashMap<>();

        for (V val : list) {
            Element<V> element = new Element<>(val);
            elementMap.put(val, element);
            fatherMap.put(element, element);
            sizeMap.put(element, 1);
        }
    }

    private Element<V> findHead(Element<V> element) {
        Deque<Element<V>> stack = new LinkedList<>();
        while (element != fatherMap.get(element)) {
            stack.push(element);
            element = fatherMap.get(element);
        }

        while (!stack.isEmpty()) {
            fatherMap.put(stack.pop(), element);
        }

        return element;
    }

    public Boolean isSameSet(V e1, V e2) {
        if (elementMap.containsKey(e1) && elementMap.containsKey(e2)) {
            return findHead(elementMap.get(e1)) == findHead(elementMap.get(e2));
        }
        return false;
    }

    public void union(V e1, V e2) {
        if (isSameSet(e1, e2)) {
            return;
        }

        Element<V> E1 = findHead(elementMap.get(e1));
        Element<V> E2 = findHead(elementMap.get(e2));

        if (E1 == E2) {
            return;
        }

        Element<V> large = sizeMap.get(E1) >= sizeMap.get(E2) ? E1 : E2;
        Element<V> small = large == E1 ? E2 : E1;
        fatherMap.put(small, large);
        sizeMap.put(large, sizeMap.get(large) + sizeMap.get(small));
        sizeMap.remove(small);
    }
}
