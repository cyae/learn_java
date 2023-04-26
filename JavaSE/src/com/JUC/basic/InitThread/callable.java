package com.JUC.basic.InitThread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class callable {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        NumberThread1 evenAdder = new NumberThread1();
        NumberThread2 oddAdder = new NumberThread2();

        FutureTask<Integer> futureTask1 = new FutureTask<>(evenAdder);
        FutureTask<Object> futureTask2 = new FutureTask<>(oddAdder);
        new Thread(futureTask1).start();
        new Thread(futureTask2).start();

        while (!futureTask1.isDone() || !futureTask2.isDone()) {
            ;
        }

        // call()方法可以有返回值，Callable接口支持泛型，runnable，run()没有
        Integer sum1 = futureTask1.get();
        System.out.println("even sum: " + sum1);

        Integer sum2 = (Integer) futureTask2.get();
        System.out.println("odd sum: " + sum2);

    }
}

// run()->Thread, Runnable
// call()->Callable->FutureTask->Thread
class NumberThread1 implements Callable<Integer> {

    @Override
    // Callable的call()方法可以抛出异常，由调用者(本例是main线程👆)捕获；run()方法不行
    public Integer call() throws Exception {
        int num = 0;
        for (int i = 0; i < 100; i++) {
            if ((i & 1) == 0) {
                System.out.println("even: " + i);
                num += i;
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(500));
            }
        }
        return num;
    }

}

class NumberThread2 implements Callable<Object> {

    @Override
    public Object call() throws Exception {
        int num = 0;
        for (int i = 0; i < 100; i++) {
            if ((i & 1) == 1) {
                System.out.println("odd: " + i);
                num += i;
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(500));
            }
        }
        return num;
    }

}
