package com.JUC.UnsafeCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;

/*
* 同时开启多条子线程对资源类ArrayList进行add操作，可能出现什么结果？ConcurrentModificationException
* add 源码为 Object[size++] = e; 等价于：
* 1. Object[size] = e
* 2. size++
* ①假设线程A执行完1，CPU使A进入就绪态(时间片用完、被抢占...)，使线程B进入执行态，则A先插入的e会被B覆盖(数据不一致)
* ②然后B、A分别执行语句2，则会出现null值
* ③假设list初始容量为10, 执行到第9个线程A，ensureCap检查还差一个才需扩容，但此时B抢占add，占满了10个空间，则A接着插入就会越界(异常)
* 💡CAP理论: 数据一致性 和 并发性 是矛盾的
*/
@SuppressWarnings("all")
public class Unsafelist {
    public static void main(String[] args) {

        // listNoSafe();

        // setNoSafe();

        // mapNoSafeOnPut();

        mapNoSafeOnGet();
    }

    /*
     * List<String> list = Collections.synchronizedList(new ArrayList<>());
     * List<String> list = new Vector<>(); // add操作加synchronized
     * List<String> list = new CopyOnWriteArrayList<>(); //
     * CopyOnWriteArrayList底层使用volatile Object[] + synchronized(lock)保护敏感操作 +
     * MySQL集群中读写分离思想
     * 上锁，先复制一份obj[]只负责写，读取操作get在原件上进行，写完再set回原数组，最后释放锁
     */
    private static void listNoSafe() {
        List<String> list = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

    private static void setNoSafe() {
        Set<String> set = new HashSet<>();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    private static void mapNoSafeOnPut() {
        Map<String, String> map = new HashMap<>();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    /*
     * hashmap初始容量16，当达到threadhold就调用resize扩容Entry[]，并对现有链表rehash
     * resize原理，复制一份新Entry[]，容量是2倍，再调用transfer()进行迁移
     * transfer原理：对Entry[]中每一个桶上的链表节点，依次使用 头插法(jdk7) 插入新table的新index
     * 头插法导致逆序，在多线程环境下，假如AB分别进入transfer，A刚标记好p和p.next就等待
     * B执行完transfer后导致A标记好的p和p.next逆序，当A唤醒后，就会导致环形链表，死循环
     */
    private static void mapNoSafeOnGet() {
        Map<String, String> map = new HashMap<>();
        for (int i = 1; i <= 60; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                map.get(Thread.currentThread().getName());
            }, String.valueOf(i)).start();
        }
    }
    /*
     * jdk8改为尾插法，不会打乱顺序，而且由全部rehash改为部分rehash，消除了此问题
     * 
     * HashTable(全部操作synchronized)、Collections.synchronizedMap(全部操作synchronized)
     * ConcurrentHashMap(二级哈希 + 分段锁思想: 将Entry[]分段, 每段一把锁. 当写某段时, 其他段读写不受影响)
     * https://blog.csdn.net/qq_29051413/article/details/107869427
     */

}
