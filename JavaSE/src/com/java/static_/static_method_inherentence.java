package com.java.static_;/* Java program to show that if static method is redefined by
							a derived class, then it is not overriding. */

// Superclass
class Base {

	// Static method in base class which will be hidden in subclass
	public static void display() {
		System.out.println("Static or class method from Base");
	}

	// Non-static method which will be overridden in derived class
	public void print() {
		System.out.println("Non-static or Instance method from Base");
	}
}

// Subclass
class Derived extends Base {

	// This method is hidden by display() in Base, NOT INHERITANCE
	// @Override <=== Wrong! NOT INHERITANCE
	public static void display() {
		System.out.println("Static or class method from Derived");
	}

	// This method overrides print() in Base
	@Override
	public void print() {
		System.out.println("Non-static or Instance method from Derived");
	}
}

// Driver class
public class static_method_inherentence {
	public static void main(String args[]) {
		Base obj1 = new Derived();

		// As per overriding rules this should call to class Derive's static
		// overridden method. Since static method can not be overridden, it
		// calls Base's display()
		Base.display();

		// Here overriding works and Derive's print() is called
		obj1.print();
	}
}
