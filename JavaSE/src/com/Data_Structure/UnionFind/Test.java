package com.Data_Structure.UnionFind;

import java.util.ArrayList;
import java.util.List;

public class Test {

    UnionFind<Integer> uf;

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        UnionFind<Integer> uf = new UnionFind<>(list);
        System.out.println(uf.isSameSet(1, 3));
        uf.union(1, 3);
        System.out.println(uf.isSameSet(1, 3));
    }
}
