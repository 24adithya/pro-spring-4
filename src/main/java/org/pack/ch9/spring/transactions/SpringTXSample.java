package org.pack.ch9.spring.transactions;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringTXSample {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:META-INF/spring/app-context-annotation-data-transactions-hibernate-home.xml");
		ctx.refresh();
		
		ContactService contactService = ctx.getBean("springTxContactService", ContactService.class);
		listContacts("Find all:", contactService.findAll());
//		saveContact(contactService);
//		countContacts(contactService);
//		listContacts("Find all:", contactService.findById(1l));
//		listContacts("Find by first name:", contactService.findByFirstName("Adams"));
//		listContacts("Find by first and last name:", contactService.findByFirstNameAndLastName("AAR1", "AAR1"));
	}

	private static void countContacts(ContactService contactService) {
		System.out.println("No. of Contacts: " + contactService.countAll());
	}

	private static void saveContact(ContactService contactService) {
		Contact contact = contactService.findById(1L).get(0);
		contact.setFirstName("Peter");
		contactService.save(contact);
		System.out.println("Contact saved successfully: " + contact);
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
