package org.pack.ch9.spring.transactions.declarative;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service("springTxDeclarativeContactService")
@Repository
//@Transactional
public class ContactService {
	@Autowired(required=true)
	private ContactRepository contactRepository;

//	@Transactional(readOnly = true)
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

//	@Transactional(readOnly = true)
	public List<Contact> findById(Long id) {
		List<Contact> contacts = new ArrayList<>();
		contacts.add(contactRepository.findOne(id));
		return contacts;
	}

//	@Transactional(propagation = Propagation.NEVER)
	public long countAll() {
		return contactRepository.countAllContacts();
	}

	public Contact save(Contact contact) {
		return contactRepository.save(contact);
	}
}
