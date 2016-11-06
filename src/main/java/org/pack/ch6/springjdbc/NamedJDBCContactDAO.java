package org.pack.ch6.springjdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class NamedJDBCContactDAO implements ContactDAO, InitializingBean {
	private DataSource dataSource;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public String findLastNameById(Long id) {
		String sql = "select last_name from contact where id = :contactId";
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("contactId", id);
		return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public void afterPropertiesSet() throws Exception {
		if (dataSource == null) {
			throw new BeanCreationException("Must set dataSource on ContactDao");
		}
		if (namedParameterJdbcTemplate == null) {
			throw new BeanCreationException("Null NamedParameterJdbcTemplate on ContactDao");
		}
	}

	public List<Contact> findAll() {
		String sql = "select id, first_name, last_name, birth_date from contact";
		// return namedParameterJdbcTemplate.query(sql, new ContactMapper());
		return namedParameterJdbcTemplate.query(sql, (rs, rowNum) -> {
			Contact contact = new Contact();
			contact.setId(rs.getLong("id"));
			contact.setFirstName(rs.getString("first_name"));
			contact.setLastName(rs.getString("last_name"));
			contact.setBirthDate(rs.getDate("birth_date"));
			return contact;
		});
	}

	public List<Contact> findByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

	public String findFirstNameById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void insert(Contact contact) {
		// TODO Auto-generated method stub

	}

	public void update(Contact contact) {
		// TODO Auto-generated method stub

	}

	public void delete(Long contactId) {
		// TODO Auto-generated method stub

	}

	private static final class ContactMapper implements RowMapper<Contact> {

		public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
			Contact contact = new Contact();
			contact.setId(rs.getLong("id"));
			contact.setFirstName(rs.getString("first_name"));
			contact.setLastName(rs.getString("last_name"));
			contact.setBirthDate(rs.getDate("birth_date"));
			return contact;
		}
	}

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:META-INF/spring/app-context-embedded-jdbc.xml");
		ctx.refresh();
		ContactDAO contactDao = ctx.getBean("contactDAO", ContactDAO.class);

		// Find last name
		System.out.println("Last name for contact id 1 is: " + contactDao.findLastNameById(1l));

		// Find all names
		List<Contact> contacts = contactDao.findAll();
		for (Contact contact : contacts) {
			System.out.println(contact);
			if (contact.getContactTelDetails() != null) {
				for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
					System.out.println("---" + contactTelDetail);
				}
			}
			System.out.println();
		}

		// Find all names with detail
		List<Contact> contactsWithDetail = contactDao.findAllWithDetail();
		for (Contact contact : contactsWithDetail) {
			System.out.println(contact);
			if (contact.getContactTelDetails() != null) {
				for (ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
					System.out.println("---" + contactTelDetail);
				}
			}
			System.out.println();
		}

		/*Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "Mark");
		map.put(2, "Tarryn");
		List<String> list = new ArrayList<String>(map.values());
		for (String s : list) {
			System.out.println(s);
		}*/

	}

	@Override
	public List<Contact> findAllWithDetail() {
		String sql = "select c.id as contact_id, c.FIRST_NAME,c.LAST_NAME,c.BIRTH_DATE, "
				+ "cd.TEL_TYPE,cd.TEL_NUMBER, cd.id as contact_tel_id "
				+ "from contact c left join contact_tel_detail cd " + "on c.id = cd.contact_id";

		return namedParameterJdbcTemplate.query(sql, new ContactWithDetailExtractor());
	}

	private static final class ContactWithDetailExtractor implements ResultSetExtractor<List<Contact>> {
		@Override
		public List<Contact> extractData(ResultSet rs) throws SQLException, DataAccessException {
			Map<Long, Contact> map = new HashMap<Long, Contact>();
			Contact contact = null;
			while (rs.next()) {
				Long contactId = rs.getLong("contact_id");
				contact = map.get(contactId);
				if (contact == null) {
					contact = new Contact();
					contact.setId(contactId);
					contact.setFirstName(rs.getString("first_name"));
					contact.setLastName(rs.getString("last_name"));
					contact.setBirthDate(rs.getDate("birth_date"));
					contact.setContactTelDetails(new ArrayList<ContactTelDetail>());
					map.put(contactId, contact);
				}

				Long contactTelDetailId = rs.getLong("contact_tel_id");
				if (contactTelDetailId > 0) {
					ContactTelDetail contactTelDetail = new ContactTelDetail();
					contactTelDetail.setId(contactTelDetailId);
					contactTelDetail.setContactId(contactId);
					contactTelDetail.setTelType(rs.getString("tel_type"));
					contactTelDetail.setTelNumber(rs.getString("tel_number"));
					contact.getContactTelDetails().add(contactTelDetail);
				}
			}
			List<Contact> contactWithDetailsList = new ArrayList < Contact > (map.values());
			return contactWithDetailsList;
		}
	}
}
