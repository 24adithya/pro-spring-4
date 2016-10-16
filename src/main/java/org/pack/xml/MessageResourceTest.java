package org.pack.xml;

import java.util.Locale;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MessageResourceTest {

	public static void main(String[] args) {

		GenericXmlApplicationContext applicationContext = new GenericXmlApplicationContext();
		applicationContext.load("classpath:META-INF/spring/app-context-xml.xml");
		applicationContext.refresh();
		
		Locale english = Locale.ENGLISH;
		Locale spanish = new Locale("es","ES");
		
		System.out.println(applicationContext.getMessage("msg", null, english));
		System.out.println(applicationContext.getMessage("nameMsg", new Object[]{"Adithya","Narayan"}, english));
		
		System.out.println(applicationContext.getMessage("msg", null, spanish));
	}

}
