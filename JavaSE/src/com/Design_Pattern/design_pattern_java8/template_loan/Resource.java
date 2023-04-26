package com.Design_Pattern.design_pattern_java8.template_loan;

import java.util.function.Consumer;

public class Resource implements AutoCloseable {
    private Resource() {
        System.out.println("before, created...");
    }

    public Resource op1() {
        System.out.println("op1");
        return this;
    }
    public Resource op2() {
        System.out.println("op2");
        return this;
    }

    @Override
    public void close() throws Exception {
        System.out.println("after, closed");
    }

    public static void use(Consumer<Resource> consumer) {
        try (Resource resource = new Resource()) {
            consumer.accept(resource);
        } catch (Exception e) {
            // SneakyThrow
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
