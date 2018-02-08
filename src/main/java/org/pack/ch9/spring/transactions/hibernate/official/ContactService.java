package org.pack.ch9.spring.transactions.hibernate.official;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//@Service("springTxContactService")
//@Transactional
public class ContactService {

	private static final Log LOG = LogFactory.getLog(ContactService.class);
	
	private ContactDAO contactDAO;
	
	public void setContactDAO(ContactDAO contactDAO) {
		this.contactDAO = contactDAO;
	}

	// Not required. We will strictly use hibernate session factory for all
	// transactions as the below value is related to repository beans
	// @Autowired
	// private ContactRepository contactRepository;

//	@Transactional(readOnly = true)
	public List<Contact> findAll() {
		return contactDAO.findAll();
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
		return contactDAO.findById(id);
	}

	/*
	 * @Transactional(propagation = Propagation.NEVER) public long countAll() {
	 * return contactRepository.countAllContacts(); }
	 */

	public Contact save(Contact contact) {
		return contactDAO.save(contact);
	}

//	@Transactional(propagation = Propagation.REQUIRED)
	public Contact saveContactInSteps(Contact contact, String newFirstName, String newLastName, Date bDate) throws Exception {
		try {
			contact.setFirstName("dummy first name");
			contact.setLastName("dummy last name");
			
			LocalDate date = LocalDate.parse("1900-01-01");
			Date dummyDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			
			contact.setBirthDate(dummyDate );
			
			contactDAO.updateContactFirstName(contact, newFirstName);
		} catch (Exception e) {
			LOG.error(e);
		}
		
		try {
			contactDAO.updateContactLastName(contact, newLastName);
		} catch (Exception e) {
			LOG.error(e);
		}
		
		try {
			contactDAO.updateContactBday(contact, bDate);
		} catch (Exception e) {
			LOG.error(e);
		}
		
		return contact;
	}

	public Contact saveById(Contact contact) {
		return contactDAO.saveById(contact);
	}
}
