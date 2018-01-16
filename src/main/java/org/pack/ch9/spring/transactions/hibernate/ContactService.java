package org.pack.ch9.spring.transactions.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

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
	
	@Autowired
	private ContactRepository contactRepository;

	@Transactional(readOnly = true)
	public List<Contact> findAll() {
		// Collection<Contact> contacts = contactRepository.findAll();
		return Lists.newArrayList(contactRepository.findAll());
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
	public List<Contact> findById(Long id) {
		List<Contact> contacts = new ArrayList<>();
		contacts.add(contactRepository.findOne(id));
		return contacts;
	}

	@Transactional(propagation = Propagation.NEVER)
	public long countAll() {
		return contactRepository.countAllContacts();
	}

	public Contact save(Contact contact) {
		return contactRepository.save(contact);
	}
}
