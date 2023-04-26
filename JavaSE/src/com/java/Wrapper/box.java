package com.java.Wrapper;

public class box {
    @SuppressWarnings("all")
    public static void main(String[] args) {
        int a = 10;
        Integer i = Integer.valueOf(a);

        // 弃用
        Integer j = new Integer(2);

        int b = i.intValue();

        // 三元运算符视为一个整体，Double会提升运算精度，所以输出1.0
        Object obj = true ? Integer.valueOf(1) : Double.valueOf(2.0);
        try {
            Integer r = (Integer) obj;
        } catch (ClassCastException e) {
            System.out.println("obj is actually a Double");
        }

        String s1 = i + "";
        String s2 = i.toString();
        String s3 = String.valueOf(i);

        Integer q = Integer.parseInt(s1);
        Integer p = Integer.valueOf(s2);
        Integer o = new Integer(s3);
    }
}
