package org.pack.ch7.springorm.official;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("contactDaoOfficial")
public class ContactDao {
	private static final Log LOG = LogFactory.getLog(ContactDao.class);
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional(readOnly = true)
	public List<Contact> findAll() {
		return sessionFactory.getCurrentSession().createQuery("from Contact c").list();
	}

	public List<Contact> findAllWithDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	public Contact findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Contact save(Contact contact) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(Contact contact) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:META-INF/spring/app-context-annotation-orm.xml");
		ctx.refresh();
		ContactDao contactDao = ctx.getBean("contactDao", ContactDao.class);
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
}
