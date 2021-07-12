package com.tow.coreJava;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.tow.libs.resourceManager;

import org.junit.Test;

class A implements Serializable {
	int i;
	String s;

	// A class constructor
	public A(int i, String s) {
		this.i = i;
		this.s = s;
	}
}

public class serialization_ex {
	
	resourceManager res = new resourceManager(); 
	
	@Test		
	public void mytest() throws IOException, ClassNotFoundException {
		A a = new A(20, "GeeksForGeeks");
		
		String oname = res.getResourcePath(); 
		// Serializing 'a'
		FileOutputStream fos = new FileOutputStream(oname+"xyz.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(a);

		// De-serializing 'a'
		FileInputStream fis = new FileInputStream(oname+"xyz.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		A b = (A) ois.readObject(); // down-casting object

		System.out.println(b.i + " " + b.s);

		// closing streams
		oos.close();
		ois.close();
	}

}
