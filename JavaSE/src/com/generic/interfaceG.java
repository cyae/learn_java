package com.generic;

// 接口泛型
interface USBable<U, R> {
    // 接口属性为psf的
    int a = 10;
    // static表明在类加载时确定👇，但泛型在对象创建时确定
    // U u = null; //错, static U u = null;

    R get(U u);

    void hi(R r);

    void run(R r1, R r2, U u1, U u2);

    default R method(U u) {
        return null;
    }
}

// 继承接口时确定泛型
interface machine extends USBable<String, Integer> {
}

// 实现接口时确定泛型
// class camera implements USBable<Float, HashMap<String, String>> {...}

class camera implements machine {

    @Override
    public Integer get(String s) {
        return 1;
    }

    @Override
    public void hi(Integer i) {
        return;
    }

    @Override
    public void run(Integer i1, Integer i2, String s1, String s2) {
        return;
    }
}
