package com.java.super_;

public class SuperTest extends A {

    public SuperTest() {
        super();
        System.out.println("son SuperTest constructor called");
    }

    public void foo() {
        // bar();
    }

    // No bar() in SuperTest, so search for bar() in father A
    // public void bar() {
    // super.callB();
    // }
}

class A {
    public int a;
    private int b;

    public A(int a) {
        this.a = a;
    }

    protected void callB() {
        System.out.println(b);
    }

    public void foo() {
        System.out.println("father foo");
    }

    // Found bar, but its private, not visible to SuperTest
    // And the search HALTS.
    @SuppressWarnings("unused")
    private void bar() {
        System.out.println("father bar");
    }

    public A() {
        System.out.println("father A constructor called");
    }

    @Override
    public String toString() {
        return "A [a=" + a + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + a;
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
        A other = (A) obj;
        if (a != other.a)
            return false;
        return true;
    }

}