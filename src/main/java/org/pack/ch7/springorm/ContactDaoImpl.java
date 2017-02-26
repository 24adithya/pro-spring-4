package org.pack.ch7.springorm;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("contactDao")
public class ContactDaoImpl implements ContactDao {
	private static final Log LOG = LogFactory.getLog(ContactDaoImpl.class);
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Contact> findAll() {
		return sessionFactory.getCurrentSession().createQuery("from contact c").list();
	}

	@Override
	public List<Contact> findAllWithDetail() {
		return sessionFactory.getCurrentSession().getNamedQuery("contact.findAllWithDetail").list();
	}

	@Override
	@Transactional(readOnly = true)
	public Contact findById(Long id) {
		return (Contact) sessionFactory.getCurrentSession().getNamedQuery("contact.findById").setParameter("id", id)
				.uniqueResult();
	}

	@Override
	public Contact save(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Contact contact) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:META-INF/spring/app-context-annotation-orm.xml");
		ctx.refresh();
		ContactDao contactDao = ctx.getBean("contactDao", ContactDao.class);
		//Find only contacts
		
		System.out.println("Find only contacts");
		listContacts(contactDao.findAll());
		System.out.println("Find contacts with details");
		listContactsWithDetail(contactDao.findAllWithDetail());
		System.out.println("Find contact by Id");
		Contact contact = contactDao.findById(1l);
		System.out.println("");
		System.out.println("Contact with id 1:" + contact);
		System.out.println("");
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
				for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
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
