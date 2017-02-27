package org.pack.ch8.springorm;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pack.ch7.springorm.Contact;
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
		return null;
	}

	public List<Contact> findAllWithDetail() {
		return null;
	}

	public Contact findById(Long id) {
		return null;
	}

	public Contact save(Contact contact) {
		return null;
	}

	public void delete(Contact contact) {
	}
}
