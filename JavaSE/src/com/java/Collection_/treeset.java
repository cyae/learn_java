package com.java.Collection_;

import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;

public class treeset {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        // 底层红黑树，当添加Node时，在树上从根开始使用Comparator/或类的comparable接口方法判断

        // 无则直接插入(实际上调用了compare(key,key)自比较，为了检测空值, treemap的key不能为空，无法比较)
        set.add(1);
        // 找到key值则添加失败
        set.add(1);

        // 大于根节点则向右TreeNode.right
        set.add(2);

        // 小于根节点则向左TreeNode.left
        set.add(-1);

        class Dummy {

            String s;
            int id;

            public Dummy(int id, String s) {
                this.id = id;
                this.s = s;
            }

            @Override
            public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + id;
                result = prime * result + ((s == null) ? 0 : s.hashCode());
                return result;
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj)
                    return true;
                if (obj == null)
                    return false;
                if (getClass() != obj.getClass())
                    return false;
                Dummy other = (Dummy) obj;
                if (id != other.id)
                    return false;
                if (s == null) {
                    if (other.s != null)
                        return false;
                } else if (!s.equals(other.s))
                    return false;
                return true;
            }

            @Override
            public String toString() {
                return "Dummy [id=" + id + ", s=" + s + "]";
            }

        }

        Dummy dummy1 = new Dummy(1001, "AA");
        Dummy dummy2 = new Dummy(1002, "BB");

        HashSet<Dummy> set1 = new HashSet<>();

        set1.add(dummy1);
        set1.add(dummy2);
        System.out.println(set1);

        System.out.println(dummy1.hashCode());
        System.out.println(dummy2.hashCode());

        // dummy1的hash变了，但不会改变其在数组中的位置，除非扩容
        dummy1.s = "CC";
        System.out.println(set1);

        System.out.println(dummy1.hashCode());
        System.out.println(dummy2.hashCode());

        // 不会删除原来的dummy1，因为按照新hash查找不到原位置
        set1.remove(dummy1);
        System.out.println(set1);

        // dummy3的hash值为dummy1的新hash值，且dummy3==dummy1
        // 虽然如此，但add的查找机制是先确定是否为空，不为空才依次遍历比较
        // dummy1还留在数组的原位置，根据新hash算出来的index为空，所以直接添加
        Dummy dummy3 = new Dummy(1001, "CC");
        System.out.println(dummy1.hashCode());
        System.out.println(dummy2.hashCode());
        System.out.println(dummy3.hashCode());
        set1.add(dummy3);
        System.out.println(set1);

        // dummy4的hash值为dummy1的原hash值，add定位到dummy1，已经有元素
        // 故开始遍历，两者现在hash不同，是不同元素，故链接在dummy1后边
        Dummy dummy4 = new Dummy(1001, "AA");
        System.out.println(dummy4.hashCode());
        set1.add(dummy4);
        System.out.println(set1);
    }
}
