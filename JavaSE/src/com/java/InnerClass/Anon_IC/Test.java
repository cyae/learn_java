package com.java.InnerClass.Anon_IC;

public class Test {
    int n = 100;

    public static void main(String[] args) {
        CellPhone cellPhone = new CellPhone();
        int n = 100;
        // 一般不允许实例化接口，但此运行类型为匿名内部类Test$1，在底层为:
        // class Test$1 implements Bell {
        // @Override
        // public void ring() {
        // System.out.println("wake up");
        // };
        // }
        // Bell bell = new Test$1();
        // cellphone的alarm调用bell即动态绑定为Test$1
        Bell bell = new Bell() {
            @Override
            public void ring() {
                System.out.println("wake up");
            };
        };
        cellPhone.alarm(bell); // 用完bell立即消失

        cellPhone.alarm(
                new Bell() {
                    @Override
                    public void ring() {
                        System.out.println("wake up");
                    }
                });

        // 如果接口中只有一个抽象方法，还可以用lambda表达式
        cellPhone.alarm(() -> System.out.println("wake up" + n));

    }

    public void foo() {
        CellPhone cellPhone = new CellPhone();
        cellPhone.alarm(() -> System.out.println("wake up" + Test.this.n));
    }
}
