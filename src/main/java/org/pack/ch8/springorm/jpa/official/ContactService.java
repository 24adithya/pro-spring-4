package org.pack.ch8.springorm.jpa.official;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("jpaContactService")
@Repository
@Transactional
public class ContactService {
	private Log log = LogFactory.getLog(ContactService.class);

	@PersistenceContext
	private EntityManager em;

	public List<Contact> findAll() {
		return em.createNamedQuery("contact.findAll", Contact.class)
				.getResultList();
	}

	public List<Contact> findAllWithDetail() {
		return em.createNamedQuery("contact.findAllWithDetail", Contact.class)
				.getResultList();
	}

	public Contact findById(Long id) {
		TypedQuery<Contact> query = em.createNamedQuery("contact.findById",
				Contact.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	public Contact save(Contact contact) {
		return null;
	}

	public void delete(Contact contact) {
	}

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:META-INF/spring/app-context-annotation-jpa-orm-official.xml");
		ctx.refresh();
		ContactService contactService = ctx.getBean("jpaContactService",
				ContactService.class);
		// findContacts(contactService);
		findContactsWithDetails(contactService);
		// findContactWithId(contactService);
		// insertContact(contactService);
		// updateContact(contactService);
		// deleteContact(contactService);
	}

	private static void deleteContact(ContactService contactService) {
		Contact contact = contactService.findById(21l);
		contactService.delete(contact);
		listContactsWithDetail(contactService.findAllWithDetail());
	}

	private static void updateContact(ContactService contactService) {
		Contact contact = contactService.findById(1l);
		contact.setFirstName("Kim Fung");
		Set<ContactTelDetail> contactTels = contact.getContactTelDetails();
		ContactTelDetail toDeleteContactTel = null;
		for (ContactTelDetail contactTel : contactTels) {
			if (contactTel.getTelType().equals("Home")) {
				toDeleteContactTel = contactTel;
			}
		}
		contact.removeContactTelDetail(toDeleteContactTel);
		contactService.save(contact);
		listContactsWithDetail(contactService.findAllWithDetail());

	}

	private static void insertContact(ContactService contactService) {
		Contact contact = new Contact();
		contact.setFirstName("Adithya");
		contact.setLastName("Narayan");
		contact.setBirthDate(new Date());
		ContactTelDetail contactTelDetail = new ContactTelDetail("Home",
				"1111111111");
		contact.addContactTelDetail(contactTelDetail);
		contactTelDetail = new ContactTelDetail("Mobile", "2222222222");
		contact.addContactTelDetail(contactTelDetail);
		contactService.save(contact);
		listContactsWithDetail(contactService.findAllWithDetail());
	}

	private static void findContactWithId(ContactService contactService) {
		System.out.println("Find contact by Id");
		Contact contact = contactService.findById(1l);
		System.out.println("Contact with id 1:" + contact);
	}

	private static void findContactsWithDetails(ContactService contactService) {
		System.out.println("Find contacts with details");
		listContactsWithDetail(contactService.findAllWithDetail());
	}

	private static void findContacts(ContactService contactService) {
		System.out.println("Find only contacts");
		listContacts(contactService.findAll());
	}

	private static void listContacts(List<Contact> contacts) {
		System.out.println("");
		System.out.println("Listing contacts without details:");
		for (Contact contact : contacts) {
			System.out.println(contact);
			System.out.println();
		}
	}

	private static void listContactsWithDetail(List<Contact> contacts) {
		System.out.println("");
		System.out.println("Listing contacts with details:");
		for (Contact contact : contacts) {
			System.out.println(contact);
			if (contact.getContactTelDetails() != null) {
				for (ContactTelDetail contactTelDetail : contact
						.getContactTelDetails()) {
					System.out.println(contactTelDetail);
				}
			}
			if (contact.getHobbies() != null) {
				for (Hobby hobby : contact.getHobbies()) {
					System.out.println(hobby);
				}
			}
			System.out.println();
		}
	}
}
