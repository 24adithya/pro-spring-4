package org.pack.ch8.springdata.jpa.home;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringJPASample {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:META-INF/spring/app-context-annotation-data-jpa-home.xml");
		ctx.refresh();
		ContactService contactService = ctx.getBean("springJpaContactService", ContactService.class);
		listContacts("Find all:", contactService.findAll());
//		listContacts("Find all:", contactService.findById(1l));
//		listContacts("Find by first name:", contactService.findByFirstName("Adams"));
//		listContacts("Find by first and last name:", contactService.findByFirstNameAndLastName("AAR1", "AAR1"));
	}

	private static void listContacts(String message, List<Contact> contacts) {
		System.out.println("");
		System.out.println(message);
		for (Contact contact : contacts) {
			System.out.println(contact);
			System.out.println();
		}
	}
}
