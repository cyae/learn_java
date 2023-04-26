package com.Design_Pattern.Proxy.StaticProxy;

// 静态代理类需要手动编写，与被代理类实现同一接口
class Proxy implements Runnable {
    private Runnable target = null;

    @Override
    public void run() {
        if (target != null) {
            target.run(); // target就是传入类，这里动态绑定，运行类型是tiger
        }
    }

    // 传入被代理类target
    public Proxy(Runnable target) {
        this.target = target;
    }

    // 传入类的run方法封装成start，这样就可以ThreadProxy.start调用了
    // 方便复用Thread的代码，统一调度方法
    public synchronized void start() {
        try {
            start0();
        } catch (Exception e) {
            //
        }
    }

    private void start0() {
        run();
    }
}
