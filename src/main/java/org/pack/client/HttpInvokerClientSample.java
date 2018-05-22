package org.pack.client;

import org.springframework.context.support.GenericXmlApplicationContext;

public class HttpInvokerClientSample {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:META-INF/spring/http-invoker-app-context.xml");
		ctx.refresh();
		SpringService springService = ctx.getBean("remoteSpringService", SpringService.class);
		System.out.println("Finding all contacts");
		ServiceRequestDTO requestDTO = new ServiceRequestDTO(new Object[] {1}, "org.pack.ch9.spring.transactions.hibernate.home.ContactService", "findAll", null);
		Object response = springService.processRequest(requestDTO);
		
		System.out.println(response);  
	}

	/*private static void listContacts(List<Contact> contacts) {
		for (Contact contact : contacts) {
			System.out.println(contact);
		}
		System.out.println("");
	}*/
}
