package com.java.Exception;

/**
 * java7 新增Supressed语法糖, 能将catch和finally捕捉的异常合并
 * try-with-resources语法糖，在字节码层面自动使用 Supressed 异常, 将多个资源关闭时的异常合并
 */
public class TryResource implements AutoCloseable {
    private final String name;

    public TryResource(String name) {
        this.name = name;
    }

    @Override
    public void close() {
        throw new RuntimeException(name);
    }

    public static void main(String[] args) {
        try (TryResource foo0 = new TryResource("Foo0"); // try-with-resources
                TryResource foo1 = new TryResource("Foo1");
                TryResource foo2 = new TryResource("Foo2")) {
            throw new RuntimeException("Initial");
        }
    }
}
