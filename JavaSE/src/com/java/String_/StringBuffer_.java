package com.java.String_;

public class StringBuffer_ {
    public static void main(String[] args) {
        String s = null;
        StringBuffer sb = new StringBuffer();

        // 从容灾性能角度考虑，不能因为append空指针就将之前添加的成果销毁
        // 所以底层优化为append{"null"}
        sb.append(s);
        System.out.println(sb);
        // 可以搭配indexOf检测是否append过空指针
        if (sb.indexOf("null") != -1) {
            System.out.println("添加过空指针！");
        }

        // 但是创建时无容灾要求，故报空指针异常
        try {
            StringBuffer sb1 = new StringBuffer(s);
            System.out.println(sb1);
        } catch (Exception e) {
            System.out.println("创建时不能使用空指针！");
        }

    }
}
