package org.pack.ch7.springorm.home;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
	private SessionFactory sessionFactoryFromBean;

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
	@Transactional(readOnly = true)
	public List<Contact> findAll(GenericXmlApplicationContext ctx) {
		sessionFactoryFromBean = (SessionFactory) ctx.getBean("sessionFactory");
		return sessionFactoryFromBean.getCurrentSession().createQuery("from contact c").list();
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
		sessionFactory.getCurrentSession().saveOrUpdate(contact);
		LOG.info("Contact saved with id:" + contact.getId());
		return contact;

	}

	@Override
	public void delete(Contact contact) {
		sessionFactory.getCurrentSession().delete(contact);
		LOG.info("Contact deleted with id: " + contact.getId());
	}

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:META-INF/spring/app-context-annotation-orm-home.xml");
		ctx.refresh();
		ContactDao contactDao = ctx.getBean("contactDao", ContactDao.class);

//		findContacts(contactDao);
		findContacts(contactDao, ctx);
//		findContactsWithDetails(contactDao);
//		findContactWithId(contactDao);
//		insertContact(contactDao);
//		updateContact(contactDao);
//		deleteContact(contactDao);
	}

	private static void findContacts(ContactDao contactDao, GenericXmlApplicationContext ctx) {
		System.out.println("Find only contacts");
		listContacts(contactDao.findAll(ctx));
	}

	private static void deleteContact(ContactDao contactDao) {
		Contact contact = contactDao.findById(4l);
		contactDao.delete(contact);
		listContactsWithDetail(contactDao.findAllWithDetail());
	}

	private static void updateContact(ContactDao contactDao) {
		Contact contact = contactDao.findById(1l);
		contact.setFirstName("Kim Fung");
		Set<ContactTelDetail> contactTels = contact.getContactTelDetails();
		ContactTelDetail toDeleteContactTel = null;
		for (ContactTelDetail contactTel : contactTels) {
			if (contactTel.getTelType().equals("Home")) {
				toDeleteContactTel = contactTel;
			}
		}
		contact.removeContactTelDetail(toDeleteContactTel);
		contactDao.save(contact);
		listContactsWithDetail(contactDao.findAllWithDetail());

	}

	private static void insertContact(ContactDao contactDao) {
		Contact contact = new Contact();
		contact.setFirstName("AAR2");
		contact.setLastName("AAR2");
		contact.setBirthDate(new Date());
		ContactTelDetail contactTelDetail = new ContactTelDetail("Home", "1111111111");
		contact.addContactTelDetail(contactTelDetail);
		contactTelDetail = new ContactTelDetail("Mobile", "2222222222");
		contact.addContactTelDetail(contactTelDetail);
		contactDao.save(contact);
		listContactsWithDetail(contactDao.findAllWithDetail());
	}

	private static void findContactWithId(ContactDao contactDao) {
		System.out.println("Find contact by Id");
		Contact contact = contactDao.findById(1l);
		System.out.println("Contact with id 1:" + contact);
	}

	private static void findContactsWithDetails(ContactDao contactDao) {
		System.out.println("Find contacts with details");
		listContactsWithDetail(contactDao.findAllWithDetail());
	}

	private static void findContacts(ContactDao contactDao) {
		System.out.println("Find only contacts");
		listContacts(contactDao.findAll());
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
