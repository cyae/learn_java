package com.JUC.CAS;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import com.Util.Person;

import org.junit.Test;

public class atom {
    public static void main(String[] args) {
        AtomicInteger a = new AtomicInteger(5);

        // 多线程进行事务...

        // CAS原理: 拿原始值initial与快照值expected比较,
        // 如果相等, 表明变量a没有被其他线程操作过, 可以安全地写回成新值, CAS返回true
        // 如果不等, 表明当前值已经不是initial, 多线程处理事务期间, 一定有其他线程操作过a, CAS返回false
        a.compareAndSet(5, 6);
        System.out.println(a.get());

        // 经过第一次CAS成功修改, a已改变, 第二次还以原始值比较就会失败
        a.compareAndSet(5, 8);
        System.out.println(a.get());
    }

    @Test
    public void testCAS() {
        // AtmoicNumber = 调用CAS原语(Unsafe类)保证原子性 + volatile保证值的可见性, 防止重排
        AtomicInteger a = new AtomicInteger(5);

        // 所有AtmoicNumber方法, 在底层都调用Unsafe类的native方法直接操作内存中地址的值
        // 这种操作是系统原语, 由若干指令组成, CPU保证原语的执行是连续不会被打断的
        a.getAndIncrement();

        // a.getAndIncrement 底层调用了 Unsafe.getAndAddInt(当前类=a.class, 偏移offset=value地址,
        // 增量delta=1)

        // public final int getAndAddInt(Object o, long offset, int delta) {
        // int v;
        // 使用do while, 表示先get再增加
        // do {
        // 不断获取当前对象a, 偏移地址为offset的内存地址所存储的值v, 相当于将堆内存中的初始共享数据拷贝到线程的栈内存中作为快照
        // v = getIntVolatile(o, offset);
        //
        // 如果循环到某一时刻, 栈内存中快照值v等于堆内存中初始值, 表明共享数据没被其他线程动过, 可以操作, 将v加上增量
        // 否则, 如果栈中快照值和堆内存中原始值不同, 则自旋继续取 最新快照值(由volatile保证可见性), 直至成功,
        // 这就保证了多线程并发操作共享资源时, 都是在最新结果上进行的, 解决了脏写
        // } while (!weakCompareAndSetInt(o, offset, v, v + delta));
        //
        // return v;

        // 由于CAS是基于逻辑上的乐观锁思想+自旋操作实现的, 因此:
        // 1. 存在乐观锁的ABA问题 --> 新增版本号, 版本号只会递增. 不再比快照值和原始值, 而是比较版本号, 不是最新版本就自旋
        // 2. 自旋消耗CPU, 即失败次数越多, 开销越大, 试想ABCD四线程: 每次A写回前, 都被BCD抢先, 则A至少自旋3次, CPU一直消耗
        // 3. 只能保证1个变量满足并发要求
    }

    @Test
    public void testCASRef() {
        // 原子引用: 一般引用类型的Atomic包装
        Person p1 = new Person("1", 1);
        Person p2 = new Person("2", 2);

        AtomicReference<Person> atomRef = new AtomicReference<>();
        atomRef.set(p1);

        atomRef.compareAndSet(p1, p2);

        System.out.println(atomRef.get());

    }

}
