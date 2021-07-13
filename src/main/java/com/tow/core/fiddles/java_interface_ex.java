package com.tow.core.fiddles;

import org.junit.Test;

interface Polygon {
	void getArea(int length, int breadth);
}

// implement the Polygon interface
class Rectangle implements Polygon {

	// implementation of abstract method
	public void getArea(int length, int breadth) {
		System.out.println("The area of the rectangle is " + (length * breadth));
	}
}

//implement the Polygon interface
class Triangle implements Polygon {

	// implementation of abstract method
	public void getArea(int length, int breadth) {
		System.out.println("The area of the triangle is " + (0.5 * length * breadth));
	}
}

// caller class and function 
public class java_interface_ex {

	@Test
	public void call() {
		Rectangle r1 = new Rectangle();
		r1.getArea(5, 6);
		Triangle r2 = new Triangle();
		r2.getArea(5, 6);
	}
}