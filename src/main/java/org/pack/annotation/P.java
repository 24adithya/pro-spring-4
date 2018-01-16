package org.pack.annotation;

import java.io.FileNotFoundException;

public class P {
	public String display() throws FileNotFoundException {
		return "P";
	}
	
	public static void main(String[] args) throws Exception {
		P p = new C();
		System.out.println(p.display() + ", " + p.displayStatic());
	}
	
	public static String displayStatic() {
		return "Static P";
	}
}

class C extends P {
	@Override
	public String display() {
		return "C";
	}
	
	public static String displayStatic() {
		return "Static C";
	}
}

