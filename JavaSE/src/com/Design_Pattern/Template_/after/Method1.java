package com.Design_Pattern.Template_.after;

@SuppressWarnings("all")
public class Method1 extends AbstractTemplate {
    @Override
    public void func() {
        String s = "";
        for (int i = 0; i < 3000; i++) {
            s += "1";
        }
    }
}
