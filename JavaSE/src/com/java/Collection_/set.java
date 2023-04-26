package com.java.Collection_;

import java.util.HashSet;

public class set {
    public static void main(String[] args) {
        // jdk8, 默认table.length==16, 👇(初次扩容)
        HashSet<Object> set = new HashSet<>();

        // set.add(key)会返回是否添加成功：
        // 1. E.hashCode()算出哈希值 👉需要重写hashcode
        // 2. 哈希值经过方法hash()算出插入位置索引index
        /**
         * int hash(int key) {
         * return (h = key.hashcode()) ^ (h >>> 16);
         * // 👉16, 尽量降低碰撞概率，让可命中范围更大，配合index👇理解
         * }
         * 
         * int index = (table.length - 1) & hash
         */
        // 3. 若Node数组中索引index处的bin：
        System.out.println(set.add("1")); // T，👉为null，则直接new Node插入

        // 否则，遍历该位置挂载的链表/红黑树Node进行依次判断，有相等则添加失败。
        // 判断方法：1. 每次new Node同时保存hash值，比较当前key和每个Node的hash
        // 2. key.equals(Node.key) 👉需要重写equals
        // 3. 如果挂载链表，添加后，立即判断此链表是否超过8个Node:
        // if 某条链表bin长>=8 && table.length>=64 👉 转化为红黑树存储
        // else if bin长>=8 &&
        // table.length < min(64, 0.75 * table.length) 👉 table数组扩容×2，重hash
        System.out.println(set.add("1")); // F，

        class Info {
            ;
        }

        System.out.println(set.add(new Info())); // T
        System.out.println(set.add(new Info())); // T

        System.out.println(set.add(new String("1"))); // F，string重写了equals，hashcode比较的是value[]
        System.out.println(set.add(new String("1"))); // F
    }
}
