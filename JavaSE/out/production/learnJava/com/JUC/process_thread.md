# Java.Util.Concurrent

底层调度时机不由代码编写顺序或jvm决定、由CPU决定

由JVM模型可知, 多线程拥有私有的VMS栈空间, 他们共享堆内存空间
因此, JVM规范规定:

1. 线程对共享变量的所有操作(Read, Write), 都必须先复制到私有的栈空间, 改完后再写回共有的堆空间
2. 多线程只能操作各自栈里的拷贝数据
3. 多线程通过主内存传值进行线程间通信

## 进程

  动态的程序就是进程

  动态体现在：

  1. 占用内存、CPU、IO等各种资源
  2. 有创建、运行、销毁等生命周期
  3. 可以交互

## 线程

将进程<==>所有系统资源

缩放到

进程下属线程<==>该进程拥有的系统资源

守护线程：当所有用户线程结束，自动结束。比如GC, word意外关闭后.sav恢复(线程一直在守护)

## 并发VS并行

并发：单核，CPU数<任务数，一定存在并发，交替执行，快，看着像并行，时间片轮转

并行：多核，CPU数>任务数，不一定并行，同时执行，更快

## 释放锁VS不释放锁 的操作

![avatar](https://img-blog.csdnimg.cn/66e3f4e85242456e846ec2a24b5be82e.png)

释放锁：

1. synchronized执行完
2. synchronized执行中遇到break,return,Error,Exception
3. 调用线程对象wait()

不释放锁：

1. sleep()、yield()占着茅坑不拉屎

## 同步监视器

一个锁资源，对于被synchronized修饰的同步方法/代码块而言，无论有多少线程调用此方法/代码块，这种锁资源都在内存中只有一个实体，保证只有一个线程能获取到该资源。

在非静态方法中，同步监视器默认为this(当前对象)；静态方法，同步监视器默认为类.Class(只加载一次)

❗如果静态方法默认的this锁不住临界资源，则改为锁共享的临界资源本身：

比如账户类Account是临界资源，而用另一个提款Drawing类实现Runnable接口，那么如果在Drawing类里用synchronized锁方法，默认锁this(即Drawing类的对象)，临界资源Account并没有被锁住。

改为锁代码块，同步监视器为account对象。

| synchronized | static方法间 | 普通方法间 | static与普通方法间 |
| :----------: | :----------: | :--------: | :----------------: |
|  同类同对象  |     竞争     |    竞争    |       不竞争       |
| 同类不同对象 |     竞争     |   不竞争   |       不竞争       |

## 编写多线程代码的思路

高内聚、低耦合 + [线程、 操作、 资源类]

## JVM所处位置

硬件->OS->JRE(JVM)->Java程序

## 锁

### 公平vs非公平

公平指按照多线程申请锁的顺序获取锁, 会排队
非公平锁直接申请, 无视队列顺序, 可能发生优先级翻转或饥饿; 如果失败, 转换为公平锁(入队)

ReentrantLock默认非公平, 非公平锁吞吐量大

### 可重入(递归锁)

ReentrantLock, synchronized
主函数加锁, 被调用函数自动获取同一把锁
可以避免死锁

### 自旋锁

获取锁失败的线程不会阻塞, 而是循环
减少上下文交换, 但消耗CPU, 适合锁时间短

手写自旋锁

```java
  AtomicReference<Thread> atom = new AtomicReference<Thread>();
  // 默认atom为空值, 通过atom.set()赋初值

  public void testSpinLock() {
      Thread thread = Thread.currentThread();
      System.out.println(Thread.currentThread().getName() + "获取自旋锁");
      while (!atom.compareAndSet(null, thread)) {
          ;
      }
  }

  public void testSpinUnlock() {
      Thread thread = Thread.currentThread();
      atom.compareAndSet(thread, null);
      System.out.println(Thread.currentThread().getName() + "释放自旋锁");
  }
```

### 独占锁(写锁)vs共享锁(读锁)

ReentrantReadWriteLock, 粒度比ReentrantLock细: 读-读共享, 写-写,写-读不共享

手写缓存

```java
class Cache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void write(String key, Object value) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "正在写入" + key);
            TimeUnit.MILLISECONDS.sleep(300);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入完成" + key);
        } catch (Exception e) {
            ;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Object read(String key) {
        Object object = null;
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "正在读取" + key);
            TimeUnit.MILLISECONDS.sleep(300);
            object = map.get(key);
            System.out.println(Thread.currentThread().getName() + "正在读取完成" + key);
        } catch (Exception e) {
            ;
        } finally {
            lock.readLock().unlock();
        }
        
        return object;
    }

    public void clear() {
        map.clear();
    }
}
```
