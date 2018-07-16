package org.pack.ch9.spring.transactions.hibernate.home;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class ContactDAO implements ApplicationContextAware {
	
	private ApplicationContext appContext;
	
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
	public void updateContactFirstName(Contact contact, String newFirstName) throws Exception {
		contact.setFirstName(newFirstName);
		sessionFactory.getCurrentSession().saveOrUpdate(contact);
//		throw new Exception("Cannot update first name");
		
		LOG.info("Contact First name saved successfully");
	}

//	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateContactLastName(Contact contact, String newLastName) throws Exception {
		contact.setLastName(newLastName);
		sessionFactory.getCurrentSession().saveOrUpdate(contact);
//		throw new Exception("Cannot update last name");
		
		LOG.info("Contact " + contact + " saved successfully");
	}
	
	public void updateContactLastName(Contact contact, String newLastName, boolean isProgrammatic) {
		if(isProgrammatic) {
			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
			// explicitly setting the transaction name is something that can only be done programmatically
			def.setName("Update last name");
			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			
			HibernateTransactionManager txMgr = appContext.getBean("transactionManager", HibernateTransactionManager.class);
			
			TransactionStatus status = txMgr.getTransaction(def);
			try {
			    // execute your business logic here
				contact.setLastName(newLastName);
				sessionFactory.getCurrentSession().saveOrUpdate(contact);
				LOG.info("Contact Last name saved successfully");
			}
			catch (Exception ex) {
				txMgr.rollback(status);
			    throw ex;
			}
//			txMgr.commit(status);
		}
	}

//	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateContactBday(Contact contact, Date bDate) /*throws Exception*/ {
		contact.setBirthDate(bDate);
		sessionFactory.getCurrentSession().saveOrUpdate(contact);
		
//		throw new Exception("Cannot update Birth date");
		
		LOG.info("Contact Birth Date saved successfully");
	}
	
	public void updateContactBday(Contact contact, Date bDate, boolean isProgrammatic) {
		if(isProgrammatic) {
			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
			// explicitly setting the transaction name is something that can only be done programmatically
			def.setName("Update BDate");
			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			
			HibernateTransactionManager txMgr = appContext.getBean("transactionManager", HibernateTransactionManager.class);
			
			TransactionStatus status = txMgr.getTransaction(def);
			try {
			    // execute your business logic here
				contact.setBirthDate(bDate);
				sessionFactory.getCurrentSession().saveOrUpdate(contact);
				txMgr.rollback(status);
				LOG.info("Contact Birth Date saved successfully");
			}
			catch (Exception ex) {
				txMgr.rollback(status);
			    throw ex;
			}
//			txMgr.commit(status);
		}
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

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.appContext = applicationContext;
	}
}
