package org.pack.ch9.spring.transactions.hibernate.official;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("springTxContactService")
@Transactional
public class ContactService {

	private static final Log LOG = LogFactory.getLog(ContactService.class);
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// Not required. We will strictly use hibernate session factory for all
	// transactions as the below value is related to repository beans
	// @Autowired
	// private ContactRepository contactRepository;

	@Transactional(readOnly = true)
	public List<Contact> findAll() {
		// Collection<Contact> contacts = contactRepository.findAll();
		return sessionFactory.getCurrentSession().createQuery("from contact_sphb c").list();
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

	@Transactional(readOnly = true)
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

	@Transactional(isolation=Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Contact saveContactInSteps(Contact contact, String newFirstName, String newLastName, Date bDate) throws Exception {
		contact.setFirstName(newFirstName);
		try {
			updateContactFirstName(contact);
		} catch (Exception e) {
			LOG.error("Contact first name could not be saved !!");
//			e.printStackTrace();
			throw e;
		}
		
		contact.setLastName(newLastName);
		try {
			updateContactLastName(contact);
		} catch (Exception e) {
			LOG.error("Contact Last name could not be saved !!");
//			e.printStackTrace();
			throw e;
		}
		
		contact.setBirthDate(bDate);
		updateContactBday(contact);
		
		return contact;
	}

	public Contact saveById(Contact contact) {
		sessionFactory.getCurrentSession().saveOrUpdate(contact);
		LOG.info("Contact " + contact + " saved successfully");
		return contact;
	}

	@Transactional(isolation=Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
	public Contact updateContactFirstName(Contact contact) throws Exception {
		throw new Exception("Cannot update first name");
//		sessionFactory.getCurrentSession().saveOrUpdate(contact);
//		LOG.info("Contact First name saved successfully");
//		return contact;
	}

	@Transactional(isolation=Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
	public Contact updateContactLastName(Contact contact) throws Exception {
		throw new Exception("Cannot update last name");
//		sessionFactory.getCurrentSession().saveOrUpdate(contact);
//		LOG.info("Contact " + contact + " saved successfully");
//		return contact;
	}

	@Transactional(isolation=Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
	public Contact updateContactBday(Contact contact) {
		sessionFactory.getCurrentSession().saveOrUpdate(contact);
		LOG.info("Contact Birth Date saved successfully");
		return contact;
	}
}
