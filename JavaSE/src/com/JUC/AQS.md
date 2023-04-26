
# 可重入锁（递归锁）

* 当某线程获取到同步监视器后，其调用方法/代码块自动获取同一个同步监视器
  * 同步监视器 = 锁计数器 + 指针，指向持有该同步监视器的线程，底层为AQS
* 底层原理：编译后，可重入锁 = monitorenter + monitorexit * 2
  * 两次monitorexit是为了防止异常
  * 当方法栈执行到monitorenter指令时
    * 若该线程已经持有同步监视器，则将锁计数器 + 1
    * 否则等待持有线程释放同步监视器
  * 当方法栈执行到monitorexit指令时，将锁计数器 - 1
    * 若锁计数器为0，则释放同步监视器
* 隐式可重入锁：synchronized
* 显示可重入锁：ReentrantLock

# 三种线程同步机制

* Object类的wait()/notify()/notifyAll()方法
  * 缺点: 必须在syncronized(Object)内部成对使用, wait/notify顺序不能改变, 否则忙等
* JUC中Condition类的await()/signal()/signalAll()方法
  * 缺点: 必须在ReentrantLock.lock()/unlock()内部成对使用, await/signal顺序不能改变, 否则忙等
* LockSupport工具类的park()/unpark(thread)静态方法
  * 可视作资源总量为1的Semaphore, 初始资源量为0, park/unpark对应P/V操作
  * 底层使用UNSAFE类的park()/unpark()native方法实现
  * 优点:
    * 随开随用LockSupport.park()/unpark(thread), 无需创建锁对象/同步监视器对象, 但也必须成对使用
    * upark只负责生成一个资源, 所以即使先unpark(thread)后park(), 也不会发生死锁

# AQS框架

* 是JUC同步机制的顶级框架, 其实现类Sync存在于:
  * ReentrantLock
  * ReentrantReadWriteLock
  * CountDownLatch
  * CyclicBarrier
  * Semaphore
* 底层实现为CLH双端队列，队列的每一个节点表示一个线程
  * 线程节点拥有状态（volatile int waitStatus, 即锁计数器）和指针（指向资源）
  * 整个框架拥有状态volatile int state, 表示排队线程数量(CLH队列长度)
  * 通过CAS, 自旋, LockSupport.park(即UNSAFE类)维护节点状态
* JUC中的各类锁, 本质上是对AQS框架的具体封装, 方便程序员调用复杂的AQS api
* 可以利用AQS框架的api实现自定义锁
* 锁的公平性: 获取资源时看CLH队列中是否有前节点predecessor, 有则等待
* Sync的上锁操作

```java
class fairSync/NonfairSync extends Sync extends AbstractQueuedSynchronizer {
  
  public void lock() {
    if (compareAndSetState(expect: 0, update: 1)) { // CAS保证volatile变量state的原子性
      setExclusiveOwnerThread(Thread.currentThread()); // 如果锁未被持有，当前线程直接拿到锁
    } else {
      acquire(1); // 否则锁已被某线程占用，当前线程尝试申请锁
    }
  }

  protected boolean acquire(int acquires) -> nonfairTryAcquire(int acquires) {
    
    Thread current = Thread.currentThread(); // 获取当前线程
    int c = getState(); // 获取AQS状态, 看是否已经有线程在使用资源

    if (c == 0) { // 如果占用线程恰好释放锁, 则直接获取锁并返回true
      if (compareAndSetState(expect: 0, update: acquires)) {
        setExclusiveOwnerThread(current);
        return true;
      }
    } else if (current == getExclusiveOwnerThread()) { // 否则, 如果当前线程已经持有锁, 锁计数器+1
      int nextc = c + acquires;
      if (nextc < 0) {
        throw new Error("Maximum lock count exceeded");
      }
      setState(nextc); // 即可重入锁
      return true;
    }
    return false; // 否则, 当前线程未持有锁, 表明锁已被占用, 返回申请锁失败false
  }

  protected boolean compareAndSetState(int expect, int update) {
    return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
  }

}
```

* 如果当前线程申请锁失败，Sync的等待(入队)操作

```java
private Node addWaiter(Node mode) {
  Node node = new Node(Thread.currentThread(), mode); // 当前线程, 尝试申请锁失败, 则构造一个等待节点入队
  // 将当前线程添加到队列尾部
  Node pred = tail;
  if (pred != null) { // 如果队列不为空
    node.prev = pred;
    if (compareAndSetTail(pred, node)) {
      pred.next = node;
      return node;
    }
  }

  enqueue(node); // 如果队列为空, 则将当前线程添加到队列头部
  return node;
}

private Node enqueue(Node node) {
  for(;;) { // 自旋, 不断尝试添加节点
    Node t = tail;
    if (t == null) { // 如果队列为空, 则初始化AQS的队列, 做法为创建dummy节点占位
      if (compareAndSetHead(expected: null, update: new Node())) {
        tail = head; 
      }
    } else { // 如果队列不为空, 即已经创建好dummy节点, 则将当前线程添加到队列尾部
      node.prev = t;
      if (compareAndSetTail(expected: t, update: node)) {
        t.next = node;
        return t;
      }
    }
  }
}

```

* 如果当前线程申请锁失败，且已完成入队，Sync的自旋式申请锁操作

```java
boolean acquireQueued(final Node node, int arg) {
  boolean failed = true;
  try {
    boolean interrupted = false;
    for (;;) { // 自旋, 不断尝试申请锁
      final Node p = node.predecessor();
      if (p == head && tryAcquire(arg)) { // 如果dummy.next等到了占用者释放的锁，则使其充当新的dummy(head.next=dummy.next, dummy.next.prev=null, dummy.next.thread=null), 并将原dummy从队列中移除，回收
        setHead(node);
        p.next = null; // help GC
        failed = false;
        return interrupted;
      }

      // 如果当前节点的前驱节点的waitStatus为SIGNAL = -1, 则shouldPark为true, 继续执行parkAndCheckInterrupt, 使用LockSupport.park()将当前线程节点挂起, 真正意义上的入队开始阻塞
      // LockSupport会一致阻塞，在这期间当前线程不再申请锁
      // 直到资源被释放(unpark)或者被中断, 当前节点才能继续自旋式申请锁
      if (shouldParkAfterFailedAcquire(p, node) && parkAndCheckInterrupt()) {
        interrupted = true;
      }
    }
  } finally {
    if (failed) {
      cancelAcquire(node);
    }
  }
}

```

* Sync的解锁操作

```java
protected boolean release(int arg) {
  if (tryRelease(arg)) { // 如果当前线程持有锁, 则释放锁
    Node h = head;
    if (h != null && h.waitStatus != 0) { // 如果队列不为空, 并且队列头部节点的waitStatus不为0, 则唤醒队列头部节点
      unparkSuccessor(h); // LockSupport.unpark(dummy.next.thread);
    }
    return true;
  }
  return false;
}

protected boolean tryRelease(int releases) {
  int c = getState() - releases;
  if (Thread.currentThread() != getExclusiveOwnerThread()) {
    throw new IllegalMonitorStateException();
  }
  boolean free = false;
  if (c == 0) {
    free = true;
    setExclusiveOwnerThread(null);
  }
  setState(c);
  return free;
}

```
