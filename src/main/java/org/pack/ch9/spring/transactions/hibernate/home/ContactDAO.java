package org.pack.ch9.spring.transactions.hibernate.home;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

public class ContactDAO {
	
	private SessionFactory sessionFactory;
	private static final Log LOG = LogFactory.getLog(ContactDAO.class);
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

//	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

//	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Contact updateContactFirstName(Contact contact, String newFirstName) throws Exception {
		contact.setFirstName(newFirstName);
		sessionFactory.getCurrentSession().saveOrUpdate(contact);
		throw new Exception("Cannot update first name");
		
//		LOG.info("Contact First name saved successfully");
//		return contact;
	}

//	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Contact updateContactLastName(Contact contact, String newLastName) throws Exception {
		contact.setLastName(newLastName);
		sessionFactory.getCurrentSession().saveOrUpdate(contact);
		throw new Exception("Cannot update last name");
		
//		LOG.info("Contact " + contact + " saved successfully");
//		return contact;
	}

//	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Contact updateContactBday(Contact contact, Date bDate) {
		contact.setBirthDate(bDate);
		sessionFactory.getCurrentSession().saveOrUpdate(contact);
		LOG.info("Contact Birth Date saved successfully");
		return contact;
	}
	
//	@Transactional(readOnly = true)
	public List<Contact> findAll() {
		// Collection<Contact> contacts = contactRepository.findAll();
		return sessionFactory.getCurrentSession().createQuery("from contact c").list();
	}

	/*
	 * @Transactional(readOnly = true) public List<Contact>
	 * findByFirstName(String firstName) { return
	 * contactRepository.findByFirstName(firstName); }
	 * 
	 * @Transactional(readOnly = true) public List<Contact>
	 * findByFirstNameAndLastName(String firstName, String lastName) { return
	 * contactRepository.findByFirstNameAndLastName(firstName, lastName); }
	 */

//	@Transactional(readOnly = true)
	public Contact findById(Long id) {
		return (Contact) sessionFactory.getCurrentSession().getNamedQuery("contact.findById").setParameter("id", id)
				.uniqueResult();
	}

	/*
	 * @Transactional(propagation = Propagation.NEVER) public long countAll() {
	 * return contactRepository.countAllContacts(); }
	 */

	public Contact save(Contact contact) {
		sessionFactory.getCurrentSession().saveOrUpdate(contact);
		LOG.info("Contact " + contact + " saved successfully");
		return contact;
	}
	
	public Contact saveById(Contact contact) {
		sessionFactory.getCurrentSession().saveOrUpdate(contact);
		LOG.info("Contact " + contact + " saved successfully");
		return contact;
	}
}
