package org.pack.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service("constructorConfusion")
public class ConstructorConfusion {
	private String someValue;

	public ConstructorConfusion(String someValue) {
		System.out.println("ConstructorConfusion(String) annotation called");
		this.someValue = someValue;
	}

	@Autowired
	public ConstructorConfusion(@Value("91") int someValue) {
		System.out.println("ConstructorConfusion(int) annotation called");
		this.someValue = "Number: " + Integer.toString(someValue);
	}

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:META-INF/spring/app-context-annotation.xml");
		ctx.refresh();
		ConstructorConfusion cc = (ConstructorConfusion) ctx.getBean("constructorConfusion");
		System.out.println(cc);
	}

	public String toString() {
		return someValue;
	}
}