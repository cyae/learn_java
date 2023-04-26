package com.Design_Pattern.Template_.after;

public class Method2 extends AbstractTemplate {
    @Override
    public void func() {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < 3000; i++) {
            sb.append("1");
        }
    }
}
