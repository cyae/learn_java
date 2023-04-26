package com.JUC.basic.InitThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.Test;

// 🟠开发中，经常面对创建和销毁线程，如果自己创建销毁，影响性能（自己造交通工具）
// 💡HashMap预处理Object[]和扩容grow思想：（公共交通工具）
// 提前创建好多个线程，存在队列中。需要用时再实现其run/call方法，达到阈值就扩容/拒绝新申请
// 用完线程不销毁，放回队列中
// 线程池大小可配corePoolSize(最小数，即使线程空闲也不销毁)，maximumPoolSize(最大可入线程数)，自动化keepAliveTime

// 线程池底层 = 构造类ThreadPoolExecutor统一调用的 阻塞队列BlockingQueue + Runnable/Callale
@SuppressWarnings("all")
public class threadPool {
    public static void main(String[] args) {
        // 了解即可
        Executors.newScheduledThreadPool(10); // 线程在等待一定时间后运行
        Executors.newWorkStealingPool(10); // jdk8, 并发性

        // 使用Executors工具类的静态方法创建线程池
        // 池内线程数固定, LinkedBlockingQueue(nThreads) + Runnable, 适合长期负载
        ExecutorService service1 = Executors.newFixedThreadPool(2);

        // 池内线程数固定为1, LinkedBlockingQueue(1) + Runnable, 适合高速串行
        ExecutorService service2 = Executors.newSingleThreadExecutor();

        // 池内线程数可变, SynchronousQueue + Runnable, 适合短期负载
        ExecutorService service3 = Executors.newCachedThreadPool();

        try {
            service1.execute(new Command()::run);
            Future<Integer> res = service1.submit(new Task()::call);
            service1.submit(() -> {
                System.out.println("hello");
            });

            System.out.println(res.get());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            service1.shutdown();
        }

    }

    @Test
    public void testThreadPoolExecutor() {
        // 实际生产中, 不会用jdk自带的线程池, 而应使用创建类ThreadPoolExecutor自己定义规则
        // 有些自带线程池maxPoolSize默认为Integer.MAX_VALUE->OOM
        // 使用LinkedBlockingQueue实现的线程池, workQueue长度默认Integer.MAX_VALUE->OOM
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(
                0, // 1. 初始常驻核心线程数, 当运行nThreads == coreSize时, 后续的线程加入workQueue
                0, // 3. 最大线程数, maxSize >= 1; 当workQueue也满时, 开始扩容, 直到maxSize
                0, // 5. 当等待时间达到此值都没有execute/submit, 扩容出来的线程被销毁, 直至剩corePoolSize个
                null, // keepAlive的单位, TimeUnit
                null, // 2. 现存线程数达到corePoolSize, 后续线程加入workQueue
                null, // 用于创建线程的工厂类
                null // 4. 拒绝策略, 当nThreads达到直到maxSize, 且workQueue也满时, 开始拒绝执行新线程
        );

        // 拒绝策略有4种:
        // ThreadPoolExecutor.AbortPolicy; // 默认, 直接RejectedExecutionException异常
        // ThreadPoolExecutor.CallerRunsPolicy; // 将申请返还给调用线程, 类似throws
        // ThreadPoolExecutor.DiscardOldestPolicy; // 抛弃workQeue中等待最久的, 然后加入申请者
        // ThreadPoolExecutor.DiscardPolicy; // 抛弃申请者

    }
}

class Command implements Runnable {
    @Override
    public void run() {
        int i = 0;
        while (true) {
            if ((i % 2) == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i + " is an even number");
            }
            if (i > 10) {
                break;
            }
            ++i;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Task implements Callable<Integer> {
    @Override
    public Integer call() {
        int i = 0;
        while (true) {
            if ((i % 2) == 1) {
                System.out.println(Thread.currentThread().getName() + ": " + i + " is an odd number");
            }
            if (i > 10) {
                break;
            }
            ++i;
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return -1;
    }
}
