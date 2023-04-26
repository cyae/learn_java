package com.JVM;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

import com.Util.Person;

import org.junit.Test;

@SuppressWarnings("all")
public class References {
    @Test
    public void strongRef() {
        Object object = new Object();
        System.gc();
    }

    @Test
    public void softRef() {
        SoftReference sf = new SoftReference(new Person("jack", 14));
        System.out.println("Before gc: " + sf.get());
        System.gc();
        System.out.println("After gc: " + sf.get());

        try {
            byte[] buffer = new byte[1024 * 1024 * 10]; // 模拟内存紧张
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Tight heap, after gc: " + sf.get());
        }
    }

    @Test
    public void weakRef() {
        WeakReference wf = new WeakReference(new Person("rose", 13));
        // Entry继承了WeakReference
        WeakHashMap<String, String> map = new WeakHashMap<>();
        map.put("123", "123");
        map.put(new String("456"), "456");
        map.put(new String("789").intern(), "789");

        System.out.println(wf.get());
        map.entrySet().forEach(System.out::println);

        System.gc();

        System.out.println("After GC:");
        System.out.println(wf.get());
        map.entrySet().forEach(System.out::println);

    }

    static References obj = null;
    static ReferenceQueue<References> rq = null;

    @Test
    public void phantomRef() {

        Thread t = new Thread(() -> {
            new Runnable() {

                @Override
                public void run() {
                    while (true) {
                        if (rq != null) {
                            PhantomReference<References> obj = null;
                            try {
                                obj = (PhantomReference<References>) rq.remove();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            if (obj != null) {
                                System.out.println("Trace: obj was GCed");
                            } else {
                                System.out.println("fail");
                            }
                        }
                    }
                }

            };
        });
        t.setDaemon(true);
        t.start();

        rq = new ReferenceQueue<>();
        obj = new References() {
            @Override
            protected void finalize() throws Throwable {
                super.finalize();
                System.out.println("调用obj的finalize方法");
                obj = this;
            }
        };

        PhantomReference<References> pr = new PhantomReference<>(obj, rq);

        try {
            System.out.println(pr.get());
            System.out.println("First GC:");
            obj = null;
            System.gc();
            Thread.sleep(1000);
            if (obj == null) {
                System.out.println("obj 已被回收");
            } else {
                System.out.println("obj 未被回收");
            }

            System.out.println("Second GC:");
            obj = null;
            System.gc();
            Thread.sleep(1000);
            if (obj == null) {
                System.out.println("obj 已被回收");
            } else {
                System.out.println("obj 未被回收");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
