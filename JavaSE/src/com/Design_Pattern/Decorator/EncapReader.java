package com.Design_Pattern.Decorator;

public class EncapReader extends AbstractReader {

    private AbstractReader abstractReader;

    public EncapReader(AbstractReader abstractReader) {
        this.abstractReader = abstractReader;
    }

    // 在不改变现有节点流的基础上整合功能
    public void readThing() {
        if (abstractReader instanceof CertainReader) {
            abstractReader.readCertainThing();
        }
        if (abstractReader instanceof SomeReader) {
            abstractReader.readSomething();
        }
    }

    public void close() {
        try {
            abstractReader.close();
            System.out.println(abstractReader.getClass() + "关闭成功");
        } catch (Exception e) {
            System.out.println("关闭失败");
        }
    }

    // 在不改变现有节点流的基础上拓展功能
    public void multiReadThing(int n) {
        for (int i = 0; i < n; i++) {
            if (abstractReader instanceof CertainReader) {
                abstractReader.readCertainThing();
            }
            if (abstractReader instanceof SomeReader) {
                abstractReader.readSomething();
            }
        }
    }

}
