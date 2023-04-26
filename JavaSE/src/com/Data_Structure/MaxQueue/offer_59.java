package com.Data_Structure.MaxQueue;

import java.util.ArrayDeque;
import java.util.Deque;

/* 
请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。

若队列为空，pop_front 和 max_value 需要返回 -1

示例 1：

输入: 
["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
[[],[1],[2],[],[],[]]
输出: [null,null,null,2,1,2]
示例 2：

输入: 
["MaxQueue","pop_front","max_value"]
[[],[],[]]
输出: [null,-1,-1]
 

限制：

1 <= push_back,pop_front,max_value的总操作数 <= 10000
1 <= value <= 10^5

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。 */
public class offer_59 {
    Deque<Integer> maxQ;
    Deque<Integer> que;

    public static void main(String[] args) {
        MaxQueue<Integer> maxQ = new MaxQueue<>();
        System.out.println(maxQ.getMax());
        System.out.println(maxQ.poll());
        System.out.println(maxQ.poll());
        maxQ.offer(94);
        System.out.println(maxQ.getMax());
        maxQ.offer(16);
        System.out.println(maxQ.getMax());
        maxQ.offer(89);
        System.out.println(maxQ.poll());
        maxQ.offer(22);
        System.out.println(maxQ.getMax());
        System.out.println(maxQ.poll());
        System.out.println(maxQ.getMax());
    }
}

class MaxQueue<T extends Comparable<T>> {

    Deque<T> maxQ;
    Deque<T> que;

    public MaxQueue() {
        this.maxQ = new ArrayDeque<>();
        this.que = new ArrayDeque<>();
    }

    public T getMax() {
        return maxQ.peekFirst();
    }

    public void offer(T value) {
        que.offer(value);
        while (!maxQ.isEmpty() && value.compareTo(maxQ.peekLast()) > 0) { // value > maxQ.peekFirst()
            maxQ.pollLast();
        }
        maxQ.offerLast(value);
    }

    public T poll() {
        if (que.isEmpty()) {
            return null; // return -1;
        }
        T t = que.poll();
        if (maxQ.size() > 0 && t.equals(maxQ.peek())) {
            maxQ.pollFirst();
        }
        return t;
    }

}
