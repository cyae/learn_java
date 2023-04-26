package com.JUC.basic.InitThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPool {
    @SuppressWarnings("all")
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                600L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        // 根据业务类型决定参数, CPU密集型 or IO密集型?
        int cpuNum = Runtime.getRuntime().availableProcessors();
        // CPU密集型-->减少切换, maxPoolSize = cpuNum
        // IO密集型 -->CPU可以切换, maxPoolSize = 2 * cpuNum
        // 或maxPoolSize = cpuNum / (1 - 0.8~0.9)
    }
}
