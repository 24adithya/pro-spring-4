package org.pack.ch9.spring.transactions.hibernate.official;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("springTxContactService")
@Transactional
public class ContactService {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
//	Not required. We will strictly use hibernate session factory for all transactions as the below value is related to repository beans  
//	@Autowired
//	private ContactRepository contactRepository;

	@Transactional(readOnly = true)
	public List<Contact> findAll() {
		// Collection<Contact> contacts = contactRepository.findAll();
//		return sessionFactory.getCurrentSession().createQuery("from contact_sphb c").list();
		List<Contact> result = findRealAll();

		return result;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public List<Contact> findRealAll() {
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

	/*@Transactional(readOnly = true)
	public List<Contact> findById(Long id) {
		List<Contact> contacts = new ArrayList<>();
		contacts.add(contactRepository.findOne(id));
		return contacts;
	}

	@Transactional(propagation = Propagation.NEVER)
	public long countAll() {
		return contactRepository.countAllContacts();
	}*/

	/*public Contact save(Contact contact) {
		return contactRepository.save(contact);
	}*/
}
