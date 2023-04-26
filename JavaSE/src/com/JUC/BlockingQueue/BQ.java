package com.JUC.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

// 阻塞队列实现Queue接口, 与List一样, 都属于Collection的子类
// 拥有与Queue一样的两组增删查方法(1)add,remove,element(2)offer,poll,peek
// 两者的区别是, 如果队列已满或为空(1)会抛异常, (2)只会返回是否成功
public class BQ {
    // 数组实现的有界阻塞队列(没有无参构造器, 必须指定容量)
    BlockingQueue<Integer> bq1 = new ArrayBlockingQueue<>(10, true);

    // 链表实现的有界阻塞队列(无参构造默认capacity=Integer.MAX_VALUE, 相当于无界, 容易OOM)
    BlockingQueue<Integer> bq2 = new LinkedBlockingQueue<>(10);

    // 单元素阻塞队列(容量固定为1)
    BlockingQueue<Integer> bq3 = new SynchronousQueue<>(false);

    //////////////////////////////////////////////////////////////////////

    // 优先级排序的无界阻塞队列
    BlockingQueue<Integer> bq4 = new PriorityBlockingQueue<>(10);

    // 链表实现的无界阻塞队列
    BlockingQueue<Integer> bq5 = new LinkedTransferQueue<>();

    // 链表实现的双向阻塞队列
    BlockingQueue<Integer> bq6 = new LinkedBlockingDeque<>();

    // 优先级排序的延迟无界阻塞队列
    BlockingQueue<Delayed> delayQueue = new DelayQueue<>();

    @Test
    public void testBlock1() {
        BlockingQueue<Integer> bq1 = new ArrayBlockingQueue<>(3);

        try {
            bq1.put(1);
            bq1.put(1);
            bq1.put(1);
            System.out.println("队列已满, 生产者试图向队列添加元素, 阻塞直到队列有空");
            bq1.put(1);

            bq1.take();
            bq1.take();
            bq1.take();
            System.out.println("队列为空, 消费者试图从队列取出元素, 阻塞直到队列非空");
            bq1.take();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBlock2() {
        BlockingQueue<Integer> bq1 = new LinkedBlockingQueue<>(3);

        try {
            bq1.offer(1, 2L, TimeUnit.SECONDS);
            bq1.offer(1, 2L, TimeUnit.SECONDS);
            bq1.offer(1, 2L, TimeUnit.SECONDS);
            System.out.println("队列已满, 生产者试图向队列添加元素, 阻塞直到队列有空或超过2秒");
            bq1.offer(1, 2L, TimeUnit.SECONDS);

            bq1.poll(2L, TimeUnit.SECONDS);
            bq1.poll(2L, TimeUnit.SECONDS);
            bq1.poll(2L, TimeUnit.SECONDS);
            System.out.println("队列为空, 消费者试图从队列取出元素, 阻塞直到队列非空或超过2秒");
            bq1.poll(2L, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        BlockingQueue<Integer> bq1 = new SynchronousQueue<>(true);

        new Thread(() -> {
            try {
                bq1.put(1);
                System.out.println("Full");
                bq1.put(1);
                System.out.println("Full");
                bq1.put(1);
                System.out.println("Full");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                bq1.take();
                System.out.println("Empty");

                TimeUnit.SECONDS.sleep(2);
                bq1.take();
                System.out.println("Empty");

                TimeUnit.SECONDS.sleep(3);
                bq1.take();
                System.out.println("Empty");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

    }
}
