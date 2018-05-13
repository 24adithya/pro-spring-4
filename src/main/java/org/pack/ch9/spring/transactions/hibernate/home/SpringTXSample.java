package org.pack.ch9.spring.transactions.hibernate.home;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringTXSample {
	
	private static final Log LOG = LogFactory.getLog(SpringTXSample.class);
	
	public static void main(String[] args) {
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:META-INF/spring/app-context-annotation-data-transactions-hibernate-home-xml.xml");
		ctx.refresh();
		
		ContactService contactService = ctx.getBean("springTxContactService", ContactService.class);
//		listContacts("Find all:", contactService.findAll());
		saveContactById(contactService);
//		saveContact(contactService);
//		listContacts("Find all:", contactService.findAll());
//		countContacts(contactService);
//		listContacts("Find all:", contactService.findById(1l));
//		listContacts("Find by first name:", contactService.findByFirstName("Adams"));
//		listContacts("Find by first and last name:", contactService.findByFirstNameAndLastName("AAR1", "AAR1"));
	}

	/*private static void countContacts(ContactService contactService) {
		System.out.println("No. of Contacts: " + contactService.countAll());
	}*/

	private static void saveContact(ContactService contactService) {
		Contact contact = contactService.findById(1L);
		contact.setFirstName("Peter");
		contactService.save(contact);
		System.out.println("Contact saved successfully: " + contact);
	}
	
	private static void saveContactById(ContactService contactService) {
		Contact contact = contactService.findById(13L);
		try {
			if(contact == null) {
				contact = new Contact();
//				contact.setId(6l);			
			}
			LocalDate date = LocalDate.parse("1988-03-24");
			Date newDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			contactService.saveContactInSteps(contact, "Adithya", "Narayan", newDate);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
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
