package org.pack.ch6.springjdbc;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository("contactDAO")
public class AnnotationNamedJDBCContactDAO implements ContactDAO {
	private static final Log LOG = LogFactory.getLog(AnnotationNamedJDBCContactDAO.class);
	private DataSource dataSource;
	private SelectAllContacts selectAllContacts;
	private SelectContactByFirstName selectContactByFirstName;
	private UpdateContact updateContact;
	private InsertContact insertContact;
	private StoredFunctionFirstNameById storedFunctionFirstNameById;

	@Override
	public List<Contact> findAll() {
		return selectAllContacts.execute();
	}

	@Override
	public List<Contact> findByFirstName(String firstName) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("first_name", firstName);
		return selectContactByFirstName.executeByNamedParam(paramMap);
	}

	@Override
	public String findFirstNameById(Long id) {
		List<String> result = storedFunctionFirstNameById.execute(id);
		return result.get(0);
	}

	@Override
	public void insert(Contact contact) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("first_name", contact.getFirstName());
		paramMap.put("last_name", contact.getLastName());
		paramMap.put("birth_date", contact.getBirthDate());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		insertContact.updateByNamedParam(paramMap, keyHolder);
		contact.setId(keyHolder.getKey().longValue());
		LOG.info("New contact inserted with id: " + contact.getId());
	}

	@Override
	public void update(Contact contact) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("first_name", contact.getFirstName());
		paramMap.put("last_name", contact.getLastName());
		paramMap.put("birth_date", contact.getBirthDate());
		paramMap.put("id", contact.getId());
		updateContact.updateByNamedParam(paramMap);
		LOG.info("Existing contact updated with id: " + contact.getId());
	}

	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.selectAllContacts = new SelectAllContacts(dataSource);
		this.selectContactByFirstName = new SelectContactByFirstName(dataSource);
		this.updateContact = new UpdateContact(dataSource);
		this.insertContact  = new InsertContact(dataSource);
		this.storedFunctionFirstNameById = new StoredFunctionFirstNameById(dataSource);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public String findLastNameById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long contactId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Contact> findAllWithDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	private static void listContacts(List<Contact> contacts) {
		for (Contact contact : contacts) {
			System.out.println(contact);
			if (contact.getContactTelDetails() != null) {
				for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
					System.out.println("---" + contactTelDetail);
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:META-INF/spring/app-context-embedded-jdbc-annotation.xml");
		ctx.refresh();
		ContactDAO contactDao = ctx.getBean("contactDAO", ContactDAO.class);

		// Find all contacts
		List<Contact> contacts = contactDao.findAll();
		listContacts(contacts);

		// Find by first name
		contacts = contactDao.findByFirstName("Chris");
		listContacts(contacts);

		// Update contact
		Contact contact = new Contact();
		contact.setId(1l);
		contact.setFirstName("Chris");
		contact.setLastName("Schaefer");
		contact.setBirthDate(new Date((new GregorianCalendar(1977, 10, 1)).getTime().getTime()));
		contactDao.update(contact);
		listContacts(contactDao.findAll());
		
		//Insert contact
		/*contact = new Contact();
		contact.setFirstName("Adithya");
		contact.setLastName("Narayan");
		contact.setBirthDate(new Date((new GregorianCalendar(1988, 24, 3)).getTime().getTime()));
		contactDao.insert(contact);
		listContacts(contactDao.findAll());*/
		
		//Find first name by ID - SQL Function
		System.out.println(contactDao.findFirstNameById(1l));
		
	}
}